package com.geekerstar.basic.domain.entity.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author geekerstar
 * @date 2021/10/14 21:03
 * @description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonTest {

    @JsonIgnore
    private String name;

    private String age;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JsonProperty("getTelephone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("setTelephone")
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
