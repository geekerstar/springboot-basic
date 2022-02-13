package com.geekerstar.basic.module.back.domain.entity.validator;

import com.geekerstar.basic.common.validator.ValidateGroup.RouteValidEnd;
import com.geekerstar.basic.common.validator.ValidateGroup.RouteValidStart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author geekerstar
 * @date 2021/8/20 13:41
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteValidator {

    @NotNull(groups = {RouteValidStart.class}, message = "始发地省id不能为空")
    private Integer startProvinceId;
    @NotNull(groups = {RouteValidEnd.class}, message = "目的地省id不能为空")
    private Integer endProvinceId;
    @NotNull(groups = {RouteValidStart.class, RouteValidEnd.class}, message = "详细地址不能为空")
    private String address;
}
