package com.geekerstar.basic.java8;

/**
 * @author geekerstar
 * @date 2021/10/22 15:05
 *
 * apply 有入参和返回值，入参为前置任务的输出
 * accept 有入参无返回值，会返回CompletableFuture
 * run 没有入参也没有返回值，同样会返回CompletableFuture
 * combine 形成一个复合的结构，连接两个CompletableFuture，并将它们的2个输出结果，作为combine的输入
 * compose 将嵌套的CompletableFuture平铺开，用来串联两个CompletableFuture
 */
public class CompletableFutureTest {
}
