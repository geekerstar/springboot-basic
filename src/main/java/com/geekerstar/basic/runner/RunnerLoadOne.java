package com.geekerstar.basic.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RunnerLoadOne implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("RunnerLoadOne");
    }
}

