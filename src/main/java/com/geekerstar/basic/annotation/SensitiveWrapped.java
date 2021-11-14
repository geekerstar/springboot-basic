package com.geekerstar.basic.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.geekerstar.basic.common.SensitiveSerialize;
import com.geekerstar.basic.constant.SensitiveEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author geekerstar
 * @date 2021/10/23 11:36
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface SensitiveWrapped {

    /**
     * 脱敏类型
     *
     * @return
     */
    SensitiveEnum value();
}
