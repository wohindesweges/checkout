package com.tryouts.entity;

import com.tryouts.dto.PricingRuleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PricingRuleTest {

	@Test
	void checkNumberOfFieldsInDTO() {
		final int length = PricingRule.class.getFields().length;
		final int lengthDto = PricingRuleDto.class.getFields().length;
		Assertions.assertEquals(length, lengthDto, "EqualNumber of fields required");
	}

	@Test
	void updateByDto() {
		final PricingRule pricingRule = new PricingRule().setPrice(0.1d).setSpecialPrice(0.2d).setThreshold(2).setId(1L).setStockItemId(new StockItem().setName("TEST"));
		final PricingRuleDto dto = pricingRule.getDto();

		Assertions.assertEquals(pricingRule, dto.getEntityModel());
	}
}