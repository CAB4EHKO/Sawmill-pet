package ru.uni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SawmillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SawmillApplication.class, args);
    }
}
