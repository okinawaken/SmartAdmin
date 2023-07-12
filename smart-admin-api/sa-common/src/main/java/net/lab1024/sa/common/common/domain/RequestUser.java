package net.lab1024.sa.common.common.domain;

import lombok.Data;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;

/**
 * 请求用户
 * 多系统用户 可以继承此类
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-12-21 19:55:07
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Data
public class RequestUser {

    /**
     * 当前请求用户id
     */
    private Long userId;

    /**
     * 当前请求用户名称
     */
    private String userName;

    /**
     * 当前请求用户类型
     */
    private UserTypeEnum userType;

    /**
     * TODO listen 准备移除
     * 为了其他地方不报错
     */
    @Deprecated
    private String ip;

    @Deprecated
    private String userAgent;
}
