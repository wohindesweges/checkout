package com.tryouts.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.tryouts.entity"} )
@EnableJpaRepositories(basePackages = {"com.tryouts.repository.jpa"})
public class CheckOutApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckOutApplication.class, args);
    }

}
