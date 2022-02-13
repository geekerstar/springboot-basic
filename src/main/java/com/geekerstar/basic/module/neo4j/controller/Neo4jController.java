package com.geekerstar.basic.module.neo4j.controller;

import com.geekerstar.basic.module.neo4j.domain.entity.ParentNode;
import com.geekerstar.basic.module.neo4j.domain.entity.RelationNode;
import com.geekerstar.basic.module.neo4j.domain.entity.SonNode;
import com.geekerstar.basic.module.neo4j.repository.ParentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author geekerstar
 * @date 2021/11/14 18:46
 */
@RestController
@RequestMapping("/node")
@Slf4j
public class Neo4jController {

    @Resource
    private ParentRepository parentRepository;

    @GetMapping(value = "/create")
    public void createNodeRelation() {
        SonNode sonNode1 = new SonNode("儿子小帅");
        SonNode sonNode2 = new SonNode("女儿小美");

        ParentNode parentNode = new ParentNode("爸爸:孙一一");

        parentNode.addRelation(sonNode1, "儿子");
        parentNode.addRelation(sonNode2, "女儿");

        parentRepository.save(parentNode);
    }

    @GetMapping(value = "/find")
    public void findNodes() {
        Iterable<ParentNode> parentNodes = parentRepository.findAll();
        for (ParentNode parentNode : parentNodes) {
            List<RelationNode> relationNodeSet = parentNode.getSonNodes();
            for (RelationNode relationNode : relationNodeSet) {
                log.info("id:" + parentNode.getId() + " name:" + parentNode.getName() + " 关系：" + relationNode.getName() + "子节点：" + relationNode.getSonNode().getName());
            }
        }
    }
}
