package com.geekerstar.basic.module.design.model;

import cn.hutool.core.util.RandomUtil;

/**
 * @author geekerstar
 * @date 2023/1/30 10:42
 *
 * 模板方法设计模式的抽象类
 */
public abstract class AbstractBusinessHandler {

    /**
     * 模板方法
     */
    public final void execute(){
        getNumber();
        handle();
        judge();
    }

    /**
     * 取号
     */
    private void getNumber(){
        System.out.println("取号-00" + RandomUtil.randomInt());

    }

    /**
     * 办理业务
     */
    public abstract void handle(); //抽象的办理业务方法，由子类实现

    /**
     * 评价
     */
    private void judge(){
        System.out.println("评价");
    }
}
