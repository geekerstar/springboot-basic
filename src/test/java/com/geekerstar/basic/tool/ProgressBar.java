package com.geekerstar.basic.tool;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2022/5/16 21:52
 */
public class ProgressBar {

    public static void progressBar() {
        char incomplete = '░'; // U+2591 Unicode Character 表示还没有完成的部分
        char complete = '█'; // U+2588 Unicode Character 表示已经完成的部分
        int total = 100;
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(total).forEach(builder::append);
        for (int i = 0; i < total; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            String percent = " " + (i + 1) + "%";
            System.out.print(progressBar + percent);
            try {
                Thread.sleep(i * 50L);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        progressBar();
    }
}
