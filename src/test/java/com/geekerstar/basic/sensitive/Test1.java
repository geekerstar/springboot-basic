package com.geekerstar.basic.sensitive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekerstar.basic.module.back.domain.entity.SensitiveUser;
import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/10/23 11:40
 *
 * https://mp.weixin.qq.com/s/xM_faApJlc7R9jRKnaoiqg
 */
public class Test1 {

    @Test
    public void test1() throws JsonProcessingException {
        SensitiveUser sensitiveUser = new SensitiveUser();
        sensitiveUser.setUserId(1l);
        sensitiveUser.setName("张三");
        sensitiveUser.setMobile("18000000001");
        sensitiveUser.setIdCard("420117200001011000008888");
        sensitiveUser.setAge(20);
        sensitiveUser.setSex("男");

        //通过jackson方式，将对象序列化成json字符串
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(sensitiveUser));
    }
}
