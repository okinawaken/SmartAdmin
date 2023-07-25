package net.lab1024.sa.admin.module.system.login.controller;

import cn.hutool.extra.servlet.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.system.login.domain.LoginEmployeeDetail;
import net.lab1024.sa.admin.module.system.login.domain.LoginForm;
import net.lab1024.sa.admin.module.system.login.service.LoginService;
import net.lab1024.sa.common.common.annoation.NoNeedLogin;
import net.lab1024.sa.common.common.constant.RequestHeaderConst;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartRequestUtil;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 员工登录
 *
 * @Author 1024创新实验室-主任:卓大
 * @Date 2021-12-15 21:05:46
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@RestController
@Api(tags = {AdminSwaggerTagConst.System.SYSTEM_LOGIN})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @NoNeedLogin
    @ApiOperation("登录 @author 卓大")
    @PostMapping("/login")
    public ResponseDTO<LoginEmployeeDetail> login(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request) {
        String ip = ServletUtil.getClientIP(request);
        String userAgent = ServletUtil.getHeaderIgnoreCase(request, RequestHeaderConst.USER_AGENT);
        return loginService.login(loginForm, ip, userAgent);
    }

    @ApiOperation("刷新用户信息（包含用户基础信息、权限信息等等）  @author 卓大")
    @GetMapping("/login/refresh")
    public ResponseDTO<String> refresh() {
        loginService.removeLoginUserDetailCache(SmartRequestUtil.getUserId());
        return ResponseDTO.ok();
    }

    @ApiOperation("获取登录结果信息  @author 卓大")
    @GetMapping("/login/getLoginInfo")
    public ResponseDTO<LoginEmployeeDetail> getLoginInfo() {
        LoginEmployeeDetail loginEmployeeDetail = loginService.getLoginUserDetailCache(SmartRequestUtil.getUserId());
        return ResponseDTO.ok(loginEmployeeDetail);
    }

    @ApiOperation("退出登录  @author 卓大")
    @GetMapping("/login/logout")
    public ResponseDTO<String> logout() {
        return loginService.logout(SmartRequestUtil.getUser());
    }

    @NoNeedLogin
    @ApiOperation("获取验证码  @author 卓大")
    @GetMapping("/login/getCaptcha")
    public ResponseDTO<CaptchaVO> getCaptcha() {
        return loginService.getCaptcha();
    }
}
