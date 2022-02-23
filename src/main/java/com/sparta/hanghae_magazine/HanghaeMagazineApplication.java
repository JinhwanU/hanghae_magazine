package com.sparta.hanghae_magazine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeMagazineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeMagazineApplication.class, args);
    }

}
