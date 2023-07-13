package net.lab1024.sa.admin.module.system.employee.service;

import cn.dev33.satoken.stp.StpInterface;
import net.lab1024.sa.admin.module.system.employee.dao.EmployeeDao;
import net.lab1024.sa.admin.module.system.employee.domain.entity.EmployeeEntity;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.admin.module.system.role.service.RoleEmployeeService;
import net.lab1024.sa.admin.module.system.role.service.RoleMenuService;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private EmployeeDao employeeDao;

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
        Long employeeId = TokenService.getUserId((String) loginId);

        // TODO listen 待做权限缓存
        EmployeeEntity employeeEntity = employeeDao.selectById(employeeId);
        List<MenuVO> menuList = this.getEmployeeMenuAndPointsList(employeeId, employeeEntity.getAdministratorFlag());
        return menuList.stream()
                .filter(e -> MenuTypeEnum.POINTS.equalsValue(e.getMenuType()))
                .map(MenuVO::getApiPerms).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
