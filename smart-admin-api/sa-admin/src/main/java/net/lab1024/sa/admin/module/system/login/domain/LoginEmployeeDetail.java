package net.lab1024.sa.admin.module.system.login.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.enumeration.GenderEnum;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工登录
 *
 * @Author 1024创新实验室: 善逸
 * @Date 2021/8/4 21:15
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ），2012-2022
 */
@Data
public class LoginEmployeeDetail implements RequestUser {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("员工id")
    private Long employeeId;

    @ApiModelPropertyEnum(UserTypeEnum.class)
    private UserTypeEnum userType;

    @ApiModelProperty("登录账号")
    private String loginName;

    @ApiModelProperty("员工名称")
    private String actualName;

    @ApiModelPropertyEnum(GenderEnum.class)
    private Integer gender;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("部门id")
    private Long departmentId;

    @ApiModelProperty("部门名称")
    private String departmentName;

    @ApiModelProperty("是否为超管")
    private Boolean administratorFlag;

    @ApiModelProperty("菜单列表")
    private List<MenuVO> menuList;

    @JsonIgnore
    private String loginPassword;

    @ApiModelProperty("上次登录id")
    private String lastLoginIp;

    @ApiModelProperty("上次登录user-agent")
    private String lastLoginUserAgent;

    @ApiModelProperty("上次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("请求ip")
    private String ip;

    @ApiModelProperty("请求user-agent")
    private String userAgent;

    @Override
    public Long getUserId() {
        return employeeId;
    }

    @Override
    public String getUserName() {
        return actualName;
    }
}
