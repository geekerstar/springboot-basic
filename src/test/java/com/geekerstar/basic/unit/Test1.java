package com.geekerstar.basic.unit;

import com.geekerstar.basic.BasicApplicationTests;
import com.geekerstar.basic.module.retry.service.RetryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author geekerstar
 * @date 2022/4/16 10:45
 */
public class Test1 extends BasicApplicationTests {

    @Autowired
    private RetryService retryService;

    @Test
    public void test1() {
        try {
            retryService.test(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
