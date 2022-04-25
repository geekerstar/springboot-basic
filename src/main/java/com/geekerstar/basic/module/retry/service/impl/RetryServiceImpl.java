package com.geekerstar.basic.module.retry.service.impl;

import com.geekerstar.basic.exception.BusinessException;
import com.geekerstar.basic.module.retry.service.RetryService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author geekerstar
 * @date 2022/4/16 10:31
 *
 * https://mp.weixin.qq.com/s/Tc7USvs_xZ6jPyyeR2jx1Q
 */
@Service
public class RetryServiceImpl implements RetryService {

    /**
     * value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     *
     * 当重试耗尽时，RetryOperations可以将控制传递给另一个回调，即RecoveryCallback。Spring-Retry还提供了@Recover注解，用于@Retryable重试失败后处理方法。如果不需要回调方法，可以直接不写回调方法，那么实现的效果是，重试次数完了后，如果还是没成功没符合业务判断，就抛出异常。
     * @param code
     * @return
     */
    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public int test(int code) throws Exception{
        System.out.println("test被调用,时间："+LocalTime.now());
        if (code==0){
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");

        return 200;
    }
}
