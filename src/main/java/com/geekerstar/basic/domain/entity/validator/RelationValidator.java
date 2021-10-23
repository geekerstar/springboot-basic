package com.geekerstar.basic.domain.entity.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RelationValidator {

    @NotBlank(message = "父亲的姓名不能为空")
    private String fatherName;
    @NotBlank(message = "父亲的姓名不能为空")
    private String motherName;
}
