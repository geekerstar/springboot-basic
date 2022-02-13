package com.geekerstar.basic.neo4j;

import com.geekerstar.basic.BasicApplicationTests;
import com.geekerstar.basic.module.neo4j.domain.entity.Neo4jPerson;
import com.geekerstar.basic.module.neo4j.service.Neo4jPersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author geekerstar
 * @date 2021/11/14 18:34
 */
public class Neo4jTest extends BasicApplicationTests {

    @Autowired
    private Neo4jPersonService neo4jPersonService;

    @Test
    public void test1() {
        Neo4jPerson person = new Neo4jPerson();
        person.setName("Geek");
        person.setMoney(12345.45);
        person.setCharacter("A");
        person.setAge(25);
        person.setDescription("哈哈哈哈");
        Neo4jPerson p1 = neo4jPersonService.save(person);
        System.out.println(p1);
        System.out.println(neo4jPersonService.getAll());
        List<Neo4jPerson> personList = neo4jPersonService.personList(1000);
        System.out.println(personList);
        List<Neo4jPerson> personList2 = neo4jPersonService.shortestPath("王启年", "九品射手燕小乙");
        System.out.println(personList2);
        List<Neo4jPerson> personList3 = neo4jPersonService.personListDept("范闲");
        for (Neo4jPerson pe : personList3) {
            System.out.println(pe);
        }
    }
}
