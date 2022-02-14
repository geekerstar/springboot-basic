package com.geekerstar.basic.module.validation.controller;

import com.geekerstar.basic.module.validation.validator.ValidationList;
import com.geekerstar.basic.util.Response;
import com.geekerstar.basic.module.validation.domain.dto.ValidationDTO;
import com.geekerstar.basic.module.metric.domain.entity.User;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author geekerstar
 * @date 2021/8/20 15:33
 * @description
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/valid")
@Api(tags = "参数校验")
@RequiredArgsConstructor
@ApiSupport(order = 8)
public class ValidationController {

    /**
     * 集合校验
     *
     * @param userList
     * @return
     */
    @PostMapping("/saveList")
    public Response<String> saveList(@RequestBody @Valid ValidationList<User> userList) {
        // 校验通过，才会执行业务逻辑处理
        return Response.success();
    }

    @GetMapping("/get")
    @ApiOperation(value = "普通GET请求", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "码值类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "value", value = "码值", paramType = "query")
    })
    public Response<String> get(
            @RequestParam @NotNull String type,
            @RequestParam(required = false) @Length(min = 6, max = 20) String value
    ) {
        return Response.success();
    }

    @PostMapping("/post")
    @ApiOperation(value = "普通POST请求", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "validationDTO", value = "入参", paramType = "body", dataType = "ValidationDTO", required = true)
    })
    public Response<String> post(
            @Valid @RequestBody ValidationDTO validationDTO
    ) {
        return Response.success();
    }
}
