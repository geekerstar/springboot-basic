package com.geekerstar.basic.module.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2021/10/14 22:35
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;

}
