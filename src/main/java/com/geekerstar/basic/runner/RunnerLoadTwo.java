package com.geekerstar.basic.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class RunnerLoadTwo implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("RunnerLoadTwo");
    }
}
