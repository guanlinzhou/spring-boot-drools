package com.springdrools;

import com.springdrools.model.Item;
import com.springdrools.model.Purchase;
import com.springdrools.service.RulesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDroolsApplicationTests {
	@Autowired
	RulesService rulesService;

	Item item1 = new Item(null, "Frosted Flakes", 100.0);
	Item item2 = new Item(null, "Bread", 200.0);
	Purchase purchase = new Purchase(Arrays.asList(new Item[]{item1, item2}), "California");

	@Test
	public void itemRules() {
		rulesService.initializeRules();
		Item result = rulesService.fireRules(item1);
		Assert.assertEquals(result.getCost(), 80.0, 0);
	}

	@Test
	public void purchaseRules() {
		rulesService.initializeRules();
		rulesService.initializeRules();
		Purchase.Result result = rulesService.firePurchaseRules(purchase);
		Assert.assertEquals(result.getDiscountsApplied().size(), 2);
		Assert.assertEquals(result.getSubtotal(), 224.0, 0);
		Assert.assertEquals(result.getTax(), 16.24, 0);
		Assert.assertEquals(result.getTotal(), 240.24, 0);
	}
}
