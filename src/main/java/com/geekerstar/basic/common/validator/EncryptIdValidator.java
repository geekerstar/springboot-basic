package com.geekerstar.basic.common.validator;

import com.geekerstar.basic.annotation.EncryptId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author geekerstar
 * @date 2021/8/20 15:37
 * @description
 */
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {

    /**
     * 由数字或者 a-f 的字母组成，32-256 长度
     */
    private static final Pattern PATTERN = Pattern.compile("^[a-f\\\\d]{32,256}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不为null才进行校验
        if (value != null) {
            Matcher matcher = PATTERN.matcher(value);
            return matcher.find();
        }
        return true;
    }
}
