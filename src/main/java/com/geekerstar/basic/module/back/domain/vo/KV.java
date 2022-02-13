package com.geekerstar.basic.module.back.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KV {
    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private Object value;
}
