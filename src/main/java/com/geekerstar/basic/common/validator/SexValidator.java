package com.geekerstar.basic.common.validator;

import com.geekerstar.basic.annotation.Sex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author geekerstar
 * @date 2021/8/20 13:18
 * @description 自定义校验
 */
public class SexValidator implements ConstraintValidator<Sex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> sexSet = new HashSet<String>();
        sexSet.add("男");
        sexSet.add("女");
        return sexSet.contains(value);
    }
}
