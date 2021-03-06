package com.geekerstar.basic.module.sensitive.domain.entity;

import com.geekerstar.basic.module.sensitive.annotation.SensitiveWrapped;
import com.geekerstar.basic.module.sensitive.enums.SensitiveEnum;
import lombok.Data;

/**
 * @author geekerstar
 * @date 2021/10/23 11:39
 */
@Data
public class SensitiveUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    @SensitiveWrapped(SensitiveEnum.MOBILE_PHONE)
    private String mobile;

    /**
     * 身份证号码
     */
    @SensitiveWrapped(SensitiveEnum.ID_CARD)
    private String idCard;

    /**
     * 年龄
     */
    private String sex;

    /**
     * 性别
     */
    private int age;
}
