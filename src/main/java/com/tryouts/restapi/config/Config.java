package com.tryouts.restapi.config;

import com.tryouts.restapi.processor.PowerInputTypeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

	@Bean
    PowerInputTypeProcessor paymentProcessor() {
        return new PowerInputTypeProcessor();
    }

//    @Bean //FOR IN MEMORY FIRST TEST
//    CommandLineRunner initDatabase( DistrictRepository repository) {
//
//        return args -> {
//            LOG.info("Preloading " + repository.save(new District("TESTBezirk1")));
//            LOG.info("Preloading " + repository.save(new District("TestBezirk2")));
//        };
//    }
}
