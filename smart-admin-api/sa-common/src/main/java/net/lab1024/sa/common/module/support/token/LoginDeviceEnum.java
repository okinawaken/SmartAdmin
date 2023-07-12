package net.lab1024.sa.common.module.support.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.lab1024.sa.common.common.enumeration.BaseEnum;

/**
 * 登录设备类型
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-11-29 19:48:35
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Getter
@AllArgsConstructor
public enum LoginDeviceEnum implements BaseEnum {

    PC(1, "电脑端"),

    ANDROID(2, "安卓"),

    APPLE(3, "苹果"),

    H5(4, "H5"),

    WX_MP(5, "微信小程序");

    private final Integer value;

    private final String desc;
}
