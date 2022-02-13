package com.geekerstar.basic.module.back.controller;

import com.geekerstar.basic.module.back.domain.common.Response;
import com.geekerstar.basic.module.back.domain.dto.OssUploadVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author geekerstar
 * @date 2021/10/2 12:59
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/swagger")
@Api(tags = "Swagger测试")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar.com", order = 284)
public class SwaggerController {

    @GetMapping("/get")
    @ApiOperation(value = "普通GET请求", notes = "")
    @ApiOperationSupport(author = "Geekerstar", order = 33)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "码值类型", paramType = "query", required = true)
    })
    public Response<String> get(
            @RequestParam String type
    ) {
        log.info("type:{}", type);
        log.debug("type:{}", type);
        log.warn("type:{}", type);
        log.error("type:{}", type);
        return Response.success();
    }

    @PostMapping("/post")
    @ApiOperation(value = "普通POST请求", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ossUploadVO", value = "入参", paramType = "body", dataType = "OssUploadVO", required = true)
    })
    public Response<String> post(
            @Validated @RequestBody OssUploadVO ossUploadVO
    ) {
        return Response.success();
    }
}
