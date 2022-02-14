package com.geekerstar.basic.module.minio.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author geekerstar
 * @date 2021/8/15 18:59
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "OssBucketPolicyConfigDTO", description = "对象存储设置")
public class OssBucketPolicyConfigDTO {

    @JsonProperty("Version")
    private String version;

    @JsonProperty("Statement")
    private List<Statement> statement;

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Builder
    public static class Statement {
        @JsonProperty("Effect")
        private String effect;
        @JsonProperty("Principal")
        private String principal;
        @JsonProperty("Action")
        private String action;
        @JsonProperty("Resource")
        private String resource;

    }
}

