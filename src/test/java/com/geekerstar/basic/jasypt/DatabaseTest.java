package com.geekerstar.basic.jasypt;

import com.geekerstar.basic.BasicApplicationTests;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTest extends BasicApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://1.14.103.90:13306/basic?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("geekerstar");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
