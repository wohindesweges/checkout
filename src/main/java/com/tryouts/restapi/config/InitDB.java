package com.tryouts.restapi.config;

import com.tryouts.restapi.entity.District;
import com.tryouts.restapi.processor.PowerInputTypeProcessor;
import com.tryouts.restapi.repo.DistrictRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB {
    private static final Logger LOG = LoggerFactory.getLogger(InitDB.class);

    @Bean
    PowerInputTypeProcessor paymentProcessor() {
        return new PowerInputTypeProcessor();
    }

    @Bean
    CommandLineRunner initDatabase(@Qualifier("small") DistrictRepository repository) {

        return args -> {
            LOG.info("Preloading " + repository.save(new District("TESTBezirk1")));
            LOG.info("Preloading " + repository.save(new District("TestBezirk2")));
        };
    }
}
