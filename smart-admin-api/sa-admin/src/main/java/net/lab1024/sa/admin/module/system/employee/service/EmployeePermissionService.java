package net.lab1024.sa.admin.module.system.employee.service;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.admin.module.system.role.dao.RoleMenuDao;
import net.lab1024.sa.admin.module.system.role.service.RoleEmployeeService;
import net.lab1024.sa.admin.module.system.role.service.RoleMenuService;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 员工权限校验
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-12-29 21:52:46
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Service
public class EmployeePermissionService implements StpInterface {

    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 员工关联权限 缓存key
     */
    private static final String USER_ROLE_CACHE_KEY = "RoleList";

    /**
     * 角色关联功能点 缓存key
     */
    private static final String ROLE_CACHE_KEY = "role:";

    /**
     * 角色关联功能点 缓存key
     */
    private static final String ROLE_PERMISSION_CACHE_KEY = "PermissionList";

    /**
     * 查询用户拥有的前端菜单项 用于登陆返回 前端动态路由配置
     *
     * @param employeeId
     * @return
     */
    public List<MenuVO> getEmployeeMenuAndPointsList(Long employeeId, Boolean administratorFlag) {
        List<Long> roleIdList = roleEmployeeService.getRoleIdList(employeeId);
        return roleMenuService.getMenuList(roleIdList, administratorFlag);
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 权限集合
        List<String> permissionList = new ArrayList<>();

        // 遍历角色列表，查询拥有的权限
        List<String> roleList = this.getRoleList(loginId, loginType);
        for (String roleId : roleList) {
            // 查询缓存
            SaSession roleSession = SaSessionCustomUtil.getSessionById(ROLE_CACHE_KEY + roleId);
            List<String> list = roleSession.get(ROLE_PERMISSION_CACHE_KEY, () -> {
                // 从数据库查询这个角色所拥有的权限列表
                return roleMenuDao.selectMenuListByRoleIdList(Lists.newArrayList(Long.parseLong(roleId)), false)
                        .stream()
                        .filter(e -> MenuTypeEnum.POINTS.equalsValue(e.getMenuType()))
                        .map(MenuEntity::getApiPerms).filter(Objects::nonNull).distinct()
                        .collect(Collectors.toList());
            });
            permissionList.addAll(list);
        }
        // 返回权限集合
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        // 查询员工关联角色缓存
        Long employeeId = TokenService.getUserId((String) loginId);
        return session.get(USER_ROLE_CACHE_KEY, () -> {
            // 数据库中查询员工角色
            return roleEmployeeService.getRoleIdList(employeeId).stream().map(String::valueOf).collect(Collectors.toList());
        });
    }

    /**
     * 清理角色关联权限 缓存
     *
     * @param roleId
     */
    public static void clearRoleCache(Long roleId) {
        SaSessionCustomUtil.deleteSessionById(ROLE_CACHE_KEY + roleId);
    }

    /**
     * 清理 员工关联角色 缓存
     *
     * @param employeeId
     */
    public static void clearUserRoleCache(Long employeeId) {
        String loginId = TokenService.generateLoginId(employeeId, UserTypeEnum.ADMIN_EMPLOYEE);
        SaSession session = StpUtil.getSessionByLoginId(loginId, false);
        if (null == session) {
            return;
        }
        session.delete(USER_ROLE_CACHE_KEY);
    }
}
