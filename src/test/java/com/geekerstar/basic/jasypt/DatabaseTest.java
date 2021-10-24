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
        String url = encryptor.encrypt("");
        String name = encryptor.encrypt("");
        String password = encryptor.encrypt("");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
