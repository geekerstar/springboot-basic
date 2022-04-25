package com.geekerstar.basic.se;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author geekerstar
 * @date 2022/4/12 22:40
 */
public class BigDecimalTest {

    @Test
    public void test1() {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println("a values is:" + a);
        System.out.println("=====================");
        BigDecimal b = new BigDecimal("0.1");
        System.out.println("b values is:" + b);
    }

    /**
     * @param obj 传入的小数
     * @return
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (obj.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00";
        } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
            return df.format(obj);
        } else {
            return df.format(obj);
        }
    }

    @Test
    public void test2() {
        System.out.println(formatToNumber(new BigDecimal("30.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));
    }
}
