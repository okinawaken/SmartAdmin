package net.lab1024.sa.admin.module.system.menu.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.SneakyThrows;
import net.lab1024.sa.admin.module.system.menu.constant.MenuTypeEnum;
import net.lab1024.sa.admin.module.system.menu.dao.MenuDao;
import net.lab1024.sa.admin.module.system.menu.domain.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能菜单业务
 *
 * @author Turbolisten
 * @date 2023/7/20 19:04
 */
@Service
public class MenuCacheService {

    @Autowired
    private MenuDao menuDao;

    private static final Cache<String, List<String>> MENU_URL_CACHE = CacheBuilder.newBuilder().build();

    /**
     * 查询数据表中 需要校验权限的url
     *
     * @return
     */
    @SneakyThrows
    public List<String> queryNeedCheckPermissionsUrl() {
        return MENU_URL_CACHE.get("MENU_URL_CACHE", () -> {
            //  TODO listen 待确定哪个字段做为url
            return menuDao.queryMenuByType(MenuTypeEnum.POINTS.getValue(), false, false)
                    .stream()
                    .map(MenuEntity::getApiPerms)
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
        });
    }

    public static void clearCache() {
        MENU_URL_CACHE.invalidateAll();
    }

}
