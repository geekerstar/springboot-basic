package com.geekerstar.basic.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author geekerstar
 * @date 2021/8/24 18:37
 * @description
 */
@Slf4j
public class SecurityTest {

    @Test
    public void test1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String geekerstar = bCryptPasswordEncoder.encode("Geekerstar");
        System.out.println("加密：" + geekerstar);
        boolean result = bCryptPasswordEncoder.matches("Geekerstar", geekerstar);
        System.out.println("比较结果：" + result);
    }
}
