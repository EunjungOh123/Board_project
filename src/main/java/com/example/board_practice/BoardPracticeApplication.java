package com.example.board_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardPracticeApplication.class, args);
    }

}
