package com.simplon.labxpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LabXpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabXpertApplication.class, args);
    }
}
