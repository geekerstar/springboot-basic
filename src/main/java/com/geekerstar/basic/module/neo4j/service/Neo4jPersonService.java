//package com.geekerstar.basic.module.neo4j.service;
//
//
//import com.geekerstar.basic.module.neo4j.domain.entity.Neo4jPerson;
//import com.geekerstar.basic.module.neo4j.repository.Neo4jPersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class Neo4jPersonService {
//    @Autowired
//    private Neo4jPersonRepository neo4jPersonRepository;
//
//    public List<Neo4jPerson> getAll() {
//        List<Neo4jPerson> datas = new ArrayList<>();
//        neo4jPersonRepository.findAll().forEach(person -> datas.add(person));
//        return datas;
//    }
//
//    public Neo4jPerson save(Neo4jPerson person) {
//        return neo4jPersonRepository.save(person);
//    }
//
//    public List<Neo4jPerson> personList(double money) {
//        return neo4jPersonRepository.personList(money);
//    }
//
//    public List<Neo4jPerson> shortestPath(String startName, String endName) {
//        return neo4jPersonRepository.shortestPath(startName, endName);
//    }
//
//    public List<Neo4jPerson> personListDept(String name) {
//        return neo4jPersonRepository.personListDept(name);
//    }
//
//
//}
