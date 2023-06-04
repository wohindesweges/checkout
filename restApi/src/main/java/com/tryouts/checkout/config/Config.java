package com.tryouts.checkout.config;

import com.tryouts.checkout.representation.StockItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
	StockItemProcessor powerInputTypeProcessor() {
        return new StockItemProcessor();
    }

}
