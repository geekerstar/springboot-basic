package com.geekerstar.basic.java8;

import com.geekerstar.basic.module.java8.Account;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2021/10/14 22:36
 * @description
 */
public class Test1 {

    /**
     * 将员工的薪资全部增加 10000
     */
    @Test
    public void test1(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900, 23, "male", "New York"));
        AccountList.add(new Account("Jack", 7000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 21, "female", "Washington"));
        AccountList.add(new Account("Anni", 8200, 24, "female", "New York"));
        AccountList.add(new Account("Owen", 9500, 25, "male", "New York"));
        AccountList.add(new Account("Alisa", 7900, 26, "female", "New York"));

        // 不改变原来员工集合的方式
        List<Account> AccountListNew = AccountList.stream().map(Account -> {
            Account AccountNew = new Account(Account.getName(), 0, 0, null, null);
            AccountNew.setSalary(Account.getSalary() + 10000);
            return AccountNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + AccountList.get(0).getName() + "-->" + AccountList.get(0).getSalary());
        System.out.println("一次改动后：" + AccountListNew.get(0).getName() + "-->" + AccountListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Account> AccountListNew2 = AccountList.stream().map(Account -> {
            Account.setSalary(Account.getSalary() + 10000);
            return Account;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + AccountList.get(0).getName() + "-->" + AccountListNew.get(0).getSalary());
        System.out.println("二次改动后：" + AccountListNew2.get(0).getName() + "-->" + AccountListNew.get(0).getSalary());
    }

    /**
     * 将两个字符数组合并成一个新的字符数组
     */
    @Test
    public void test2(){
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }

    /**
     * 求所有员工的工资之和和最高工资
     */
    @Test
    public void test4(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900, 23, "male", "New York"));
        AccountList.add(new Account("Jack", 7000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 21, "female", "Washington"));
        AccountList.add(new Account("Anni", 8200, 24, "female", "New York"));
        AccountList.add(new Account("Owen", 9500, 25, "male", "New York"));
        AccountList.add(new Account("Alisa", 7900, 26, "female", "New York"));

        // 求工资之和方式1：
        Optional<Integer> sumSalary = AccountList.stream().map(Account::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = AccountList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
                (sum1, sum2) -> sum1 + sum2);
        // 求工资之和方式3：
        Integer sumSalary3 = AccountList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方式1：
        Integer maxSalary = AccountList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = AccountList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);
    }

    /**
     * 统计员工人数、平均工资、工资总额、最高工资
     */
    @Test
    public void test5(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900, 23, "male", "New York"));
        AccountList.add(new Account("Jack", 7000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        Long count = AccountList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = AccountList.stream().collect(Collectors.averagingDouble(Account::getSalary));
        // 求最高工资
        Optional<Integer> max = AccountList.stream().map(Account::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = AccountList.stream().collect(Collectors.summingInt(Account::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = AccountList.stream().collect(Collectors.summarizingDouble(Account::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }

    /**
     * 将员工按薪资是否高于 8000 分为两部分；将员工按性别和地区分组
     */
    @Test
    public void test6(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900,22, "male", "New York"));
        AccountList.add(new Account("Jack", 7000,23, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 12,"female", "Washington"));
        AccountList.add(new Account("Anni", 8200,34, "female", "New York"));
        AccountList.add(new Account("Owen", 9500,55, "male", "New York"));
        AccountList.add(new Account("Alisa", 7900, 44,"female", "New York"));

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Account>> part = AccountList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Account>> group = AccountList.stream().collect(Collectors.groupingBy(Account::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Account>>> group2 = AccountList.stream().collect(Collectors.groupingBy(Account::getSex, Collectors.groupingBy(Account::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }

    @Test
    public void test7(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900, 23, "male", "New York"));
        AccountList.add(new Account("Jack", 7000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 21, "female", "Washington"));

        String names = AccountList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    @Test
    public void test8(){
        List<Account> AccountList = new ArrayList<Account>();
        AccountList.add(new Account("Tom", 8900, 23, "male", "New York"));
        AccountList.add(new Account("Jack", 7000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = AccountList.stream().collect(Collectors.reducing(0, Account::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = AccountList.stream().map(Account::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /**
     * 将员工按工资由高到低（工资一样则按年龄由大到小）排序
     */
    @Test
    public void test9(){
        List<Account> AccountList = new ArrayList<Account>();

        AccountList.add(new Account("Sherry", 9000, 24, "female", "New York"));
        AccountList.add(new Account("Tom", 8900, 22, "male", "Washington"));
        AccountList.add(new Account("Jack", 9000, 25, "male", "Washington"));
        AccountList.add(new Account("Lily", 8800, 26, "male", "New York"));
        AccountList.add(new Account("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序（自然排序）
        List<String> newList = AccountList.stream().sorted(Comparator.comparing(Account::getSalary)).map(Account::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = AccountList.stream().sorted(Comparator.comparing(Account::getSalary).reversed())
                .map(Account::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = AccountList.stream()
                .sorted(Comparator.comparing(Account::getSalary).thenComparing(Account::getAge)).map(Account::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = AccountList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Account::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }
}
