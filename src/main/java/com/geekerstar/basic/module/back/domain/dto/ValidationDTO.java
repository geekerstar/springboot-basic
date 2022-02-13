package com.geekerstar.basic.module.back.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author geekerstar
 * @date 2021/10/3 20:55
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ValidationDTO", description = "参数校验入参")
public class ValidationDTO {

    @NotNull(message = "classId 不能为空")
    @Length(min = 2, max = 10)
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;
}
