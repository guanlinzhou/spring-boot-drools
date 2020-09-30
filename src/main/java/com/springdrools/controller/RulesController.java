package com.springdrools.controller;

import com.springdrools.model.Item;
import com.springdrools.model.Purchase;
import com.springdrools.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    @Autowired
    RulesService rulesService;

    @PostMapping("/item")
    public Item index(@RequestBody Item item) {
        Item finished = this.rulesService.fireRules(item);
        System.out.println(item.toString());
        return finished;
    }

    @PostMapping("/purchase")
    public Purchase.Result totalCost(@RequestBody Purchase purchase) {
        Purchase.Result finished = this.rulesService.firePurchaseRules(purchase);
        return finished;
    }
}
