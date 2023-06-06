package com.tryouts.checkout.config;

import com.tryouts.checkout.representation.ErrorDTOProcessor;
import com.tryouts.checkout.representation.StockItemProcessor;
import com.tryouts.checkout.representation.SumForCheckoutProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	StockItemProcessor stockItemProcessor() {
		return new StockItemProcessor();
	}

	@Bean
	ErrorDTOProcessor errorDTOProcessor() {
		return new ErrorDTOProcessor();
	}

	@Bean
	SumForCheckoutProcessor sumForCheckoutProcessor() {
		return new SumForCheckoutProcessor();
	}

}
