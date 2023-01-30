package com.geekerstar.basic.module.design.model.java8;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2023/1/30 10:58
 */
public class SupplierTest {

    /**
     * Supplier
     */
    public List<String> getList() {
        return Lists.newArrayList();
    }

    Supplier<List<String>> listSupplier = ArrayList::new;

    /**
     * Consumer
     */
    public void sum(String a1) {
        System.out.println(a1);
    }

    Consumer<String> printConsumer = System.out::println;

    Consumer<String> stringConsumer = (s) -> System.out.println(s.length());

    public static void main(String[] args) {
//        Consumer<String> stringConsumer = (s) -> System.out.println(s.length());
//        Stream.of("ab", "abc", "a", "abcd").forEach(stringConsumer);


//        BankBusinessHandler businessHandler = new BankBusinessHandler();
//        businessHandler.save(new BigDecimal("1000"));

        BankBusinessHandler businessHandler = new BankBusinessHandler();
        businessHandler.saveVipSupplier(new BigDecimal("1000"));
    }

}
