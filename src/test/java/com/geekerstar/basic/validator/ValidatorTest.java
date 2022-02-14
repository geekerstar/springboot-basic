package com.geekerstar.basic.validator;

import com.geekerstar.basic.module.validation.validator.ValidateGroup;
import com.geekerstar.basic.module.validation.validator.RouteValidator;
import com.geekerstar.basic.util.CommonUtil;
import org.junit.jupiter.api.Test;

/**
 * @author geekerstar
 * @date 2021/8/20 13:43
 * @description https://mp.weixin.qq.com/s/teYC1OUOmGQxAb27JRIY4A
 */
public class ValidatorTest {
    @Test
    public void test() {
        RouteValidator route = RouteValidator.builder().build();
        String errorMsg = CommonUtil.getErrorResult(route, ValidateGroup.RouteValidStart.class);
        // 始发地省id不能为空 详细地址不能为空
        System.out.println(errorMsg);

        route = RouteValidator.builder().startProvinceId(1).address("详细地址").build();
        errorMsg = CommonUtil.getErrorResult(route, ValidateGroup.RouteValidStart.class);
        // ""
        System.out.println(errorMsg);

        route = RouteValidator.builder().address("详细地址").build();
        errorMsg = CommonUtil.getErrorResult(route, ValidateGroup.RouteValidEnd.class);
        // 目的地省id不能为空
        System.out.println(errorMsg);
    }
}
