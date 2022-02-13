package com.geekerstar.basic.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geekerstar.basic.module.back.domain.entity.User;
import com.geekerstar.basic.module.back.domain.entity.json.JsonTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author geekerstar
 * @date 2021/10/14 20:49
 * @description
 */
@Slf4j
public class Test1 {

    @Test
    public void test1() throws JsonProcessingException {
        String json = "{\n" +
                "    \"username\":\"felord.cn\",\n" +
                "    \"age\":18\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.readValue(json, ObjectNode.class);
        jsonNodes.put("gender", "male");
        String newJson = objectMapper.writeValueAsString(jsonNodes);
        System.out.println(newJson);
    }

    @Test
    public void test2() throws JsonProcessingException {
        User user = new User();
        user.setId(0L);
        user.setName("geek");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.valueToTree(user);
        jsonNode.put("gender", "male");
        String s = objectMapper.writeValueAsString(jsonNode);
        System.out.println(s);
    }

    @Test
    public void test3() throws JsonProcessingException {
        JsonTest jsonTest = new JsonTest();
        jsonTest.setName("name");
        jsonTest.setAge("age");
        jsonTest.setPhone("phone");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.valueToTree(jsonTest);
        String s = objectMapper.writeValueAsString(jsonNode);
        System.out.println(s);
    }

    @Test
    public void test4() throws JsonProcessingException {
        String jsonStr = "{\"name\":\"zhansan\",\"age\":100,\"schools\":[{\"name\":\"tsinghua\",\"location\":\"beijing\"},{\"name\":\"pku\",\"location\":\"beijing\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);

        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        System.out.println("name is " + name + " age is " + age);

        JsonNode schoolsNode = jsonNode.get("schools");
        for (int i = 0; i < schoolsNode.size(); i++) {
            String schooleName = schoolsNode.get(i).get("name").asText();
            String schooleLocation = schoolsNode.get(i).get("location").asText();
            System.out.println("school Name is " + schooleName + " school Location is " + schooleLocation);
        }
    }
}
