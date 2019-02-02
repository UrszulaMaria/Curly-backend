package com.example.curly1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Curly1Application {

    public static void main(String[] args) {
        SpringApplication.run(Curly1Application.class, args);
    }

}

