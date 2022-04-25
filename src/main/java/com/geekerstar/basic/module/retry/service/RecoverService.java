package com.geekerstar.basic.module.retry.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2022/4/16 10:41
 */
@Service
public class RecoverService {

    /**
     * 方法的返回值必须与@Retryable方法一致
     * 方法的第一个参数，必须是Throwable类型的，建议是与@Retryable配置的异常一致，其他的参数，需要哪个参数，写进去就可以了（@Recover方法中有的）
     * 该回调方法与重试方法写在同一个实现类里面
     *
     * 注意：
     * 由于是基于AOP实现，所以不支持类里自调用方法
     * 如果重试失败需要给@Recover注解的方法做后续处理，那这个重试的方法不能有返回值，只能是void
     * 方法内不能使用try catch，只能往外抛异常
     * @ Recover注解来开启重试失败后调用的方法(注意,需跟重处理方法在同一个类中)，此注解注释的方法参数一定要是@Retryable抛出的异常，否则无法识别，可以在该方法中进行日志处理。
     * @param e
     * @param code
     * @return
     */
    @Recover
    public int recover(Exception e, int code){
        System.out.println("回调方法执行！！！！");
        //记日志到数据库 或者调用其余的方法
        return 400;
    }
}
