package net.lab1024.sa.common.module.support.token;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import net.lab1024.sa.common.common.constant.StringConst;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 与用户token的相关的服务
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-11-29 19:48:35
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Component
public class TokenService {

    public static final String EXTRA_KEY_USER_NAME = "userName";

    public static final String EXTRA_KEY_USER_TYPE = "userType";

    /**
     * 生成Token
     *
     * @param userId
     * @param userName
     * @param userTypeEnum
     * @param loginDeviceEnum
     * @return
     */
    public String generateToken(Long userId,
                                String userName,
                                UserTypeEnum userTypeEnum,
                                LoginDeviceEnum loginDeviceEnum) {

        /**
         * 设置登录模式参数
         * 具体参数 @see SaLoginModel 属性
         * 已经写的挺清楚的了
         */
        SaLoginModel loginModel = new SaLoginModel();
        // 此次登录的客户端设备类型, 用于[同端互斥登录]时指定此次登录的设备类型
        loginModel.setDevice(String.valueOf(loginDeviceEnum.getDesc()));
        // 扩展参数 只在 jwt 模式下 有效
        loginModel.setExtra(EXTRA_KEY_USER_NAME, userName);
        loginModel.setExtra(EXTRA_KEY_USER_TYPE, userTypeEnum.getValue());

        String loginId = generateLoginId(userId, userTypeEnum);
        StpUtil.login(loginId, loginModel);
        return StpUtil.getTokenValue();
    }

    public static String generateLoginId(Long userId, UserTypeEnum userType) {
        return userType.getValue() + StringConst.HORIZONTAL + userId;
    }

    public static Long getUserId(String loginId) {
        return Long.valueOf(loginId.substring(loginId.indexOf(StringConst.HORIZONTAL) + 1));
    }

    /**
     * 退出登录 注销
     */
    public void removeToken() {
        StpUtil.logout();
    }

    public void removeToken(Long userId, UserTypeEnum userType) {
        StpUtil.logout(generateLoginId(userId, userType));
    }

    public void removeToken(List<Long> userIdList, UserTypeEnum userType) {
        userIdList.forEach(id -> StpUtil.logout(generateLoginId(id, userType)));
    }
}