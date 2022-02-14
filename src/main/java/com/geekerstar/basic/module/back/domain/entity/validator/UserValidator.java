package com.geekerstar.basic.module.back.domain.entity.validator;

import com.geekerstar.basic.module.validation.annotation.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author geekerstar
 * @date 2021/8/20 13:06
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserValidator {
    @NotEmpty(message = "登陆账号不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String userName;

    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空！")
    private String email;

    @NotBlank(message = "密码不能为空！")
    @Size(min = 8, max = 16, message = "请输入长度在8～16位的密码")
    private String userPwd;

    @NotBlank(message = "确认密码不能为空！")
    private String confirmPwd;

    /**
     * 自定义注解校验
     */
    @Sex(message = "性别输入有误！")
    private String sex;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phone;

    /**
     * 嵌套校验
     */
    @NotNull(message = "父母名字不能为空")
    @Valid
    private RelationValidator relation;
}
