package com.geekerstar.basic.module.design.model.java8;

import cn.hutool.core.util.RandomUtil;
import com.geekerstar.basic.module.design.model.AbstractBusinessHandler;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author geekerstar
 * @date 2023/1/30 11:08
 */
public class BankBusinessHandler {

    private void execute(Consumer<BigDecimal> consumer) {
        getNumber();
        consumer.accept(null);
        judge();
    }

    protected void execute(Supplier<String> supplier, Consumer<BigDecimal> consumer) {
        String number = supplier.get();
        System.out.println(number);
        if (number.startsWith("vip")) {
            //Vip号分配到VIP柜台
            System.out.println("Assign To Vip Counter");

        } else if (number.startsWith("reservation")) {
            //预约号分配到专属客户经理
            System.out.println("Assign To Exclusive Customer Manager");
        }else{
            //默认分配到普通柜台
            System.out.println("Assign To Usual Manager");
        }
        consumer.accept(null);
        judge();
    }

    private void getNumber() {
        System.out.println("number-00" + RandomUtil.randomInt());
    }

    private void judge() {
        System.out.println("评价");
    }


    public void save(BigDecimal amount) {
        execute(a -> System.out.println("存款 " + amount));
    }

    public void draw(BigDecimal amount) {
        execute(a -> System.out.println("取款 " + amount));
    }

    public void saveVipSupplier(BigDecimal amount) {

        execute(() -> "vipNumber-00" + RandomUtil.randomInt(), a -> System.out.println("save " + amount));

    }

    public void saveSupplier(BigDecimal amount) {

        execute(() -> "number-00" + RandomUtil.randomInt(), a -> System.out.println("save " + amount));

    }

    public void saveReservationSupplier(BigDecimal amount) {

        execute(() -> "reservationNumber-00" + RandomUtil.randomInt(), a -> System.out.println("save " + amount));

    }
}
