package com.geekerstar.basic.jasypt;

import com.geekerstar.basic.BasicApplicationTests;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTest extends BasicApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    /**
     * 1、部署时传入盐值 java -jar xxx.jar  -Djasypt.encryptor.password=Y6M9fAJQdU7jNp5MW
     * 2、环境变量设置盐值
     * 打开/etc/profile文件
     * vim /etc/profile
     * 在profile文件末尾插入salt(盐)变量
     * export JASYPT_PASSWORD = Y6M9fAJQdU7jNp5MW
     * 编译，使配置文件生效
     * source /etc/profile
     * 运行
     * java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
     */
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
