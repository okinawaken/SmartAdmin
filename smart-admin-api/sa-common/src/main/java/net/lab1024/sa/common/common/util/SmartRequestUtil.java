package net.lab1024.sa.common.common.util;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import net.lab1024.sa.common.common.domain.RequestUser;

/**
 * 请求用户  工具类
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2022-05-30 21:22:12
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
public class SmartRequestUtil {

    private static final String STORAGE_KEY = "user";

    public static void setUser(RequestUser user) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(STORAGE_KEY, user);
    }

    /**
     * 获取 当前 token 请求用户
     *
     * @return
     */
    public static RequestUser getUser() {
        SaStorage storage = SaHolder.getStorage();
        return storage.getModel(STORAGE_KEY, RequestUser.class);
    }

    public static Long getUserId() {
        RequestUser user = getUser();
        return null != user ? user.getUserId() : null;
    }


}
