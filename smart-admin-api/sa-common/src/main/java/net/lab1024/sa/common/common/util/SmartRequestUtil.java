package net.lab1024.sa.common.common.util;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SmartRequestUtil {
    public static RequestUser getUser() {
        // TODO listen
        return null;
    }

    public static Long getUserId() {
        RequestUser requestUser = getUser();
        return null == requestUser ? null : requestUser.getUserId();
    }

    public static void remove() {
        // TODO listen
    }


}
