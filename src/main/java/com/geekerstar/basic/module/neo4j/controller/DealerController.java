//package com.geekerstar.basic.module.neo4j.controller;
//
//import com.geekerstar.basic.module.neo4j.domain.entity.Dealer;
//import com.geekerstar.basic.module.neo4j.domain.entity.DealerRelationShip;
//import com.geekerstar.basic.module.neo4j.repository.DealerRepository;
//import com.geekerstar.basic.module.neo4j.repository.DealerShipRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/dealer")
//@Slf4j
//public class DealerController {
//
//    @Autowired
//    private DealerRepository dealerRepository;
//    @Autowired
//    private DealerShipRepository dealerShipRepository;
//
//    @GetMapping(value = "/create")
//    @Transactional(rollbackFor = Exception.class)
//    public void createNodeRelation() {
//
//        Dealer d1 = new Dealer();
//        d1.setDealerId(1L);
//        d1.setNikeName("马云");
//        d1.setLabelName("特约联合创始人");
//        d1.setDealerPrice(170.00);
//        d1.setCreateTime(LocalDateTime.now());
//        Dealer D1 = dealerRepository.save(d1);
//
//        Dealer d2 = new Dealer();
//        d2.setDealerId(2L);
//        d2.setNikeName("张勇");
//        d2.setLabelName("联合创始人");
//        d2.setDealerPrice(180.00);
//        d1.setCreateTime(LocalDateTime.now());
//        Dealer D2 = dealerRepository.save(d2);
//
//        Dealer d11 = dealerRepository.findByDealerId(1L);
//        Dealer d22 = dealerRepository.findByDealerId(2L);
//        DealerRelationShip dealerRelationShip = new DealerRelationShip(d11, d22, "推广", LocalDateTime.now(), null);
//        dealerShipRepository.save(dealerRelationShip);
//    }
//
//    @GetMapping(value = "findByName/{labelName}")
//    public Dealer findByName(@PathVariable("labelName") String labelName) {
//        return dealerRepository.findByLabelName(labelName);
//    }
//
//    @GetMapping(value = "/find/{dealerId}")
//    public Object findByDealerId(@PathVariable("dealerId") Long dealerId) {
//        Dealer dealer = dealerRepository.findByDealerId(dealerId);
//        System.out.println(dealer);
//        return dealer;
//    }
//}
