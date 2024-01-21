package com.simplon.labxpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Main class of the application.
 * @EnableScheduling is used to enable the scheduling of the tasks like the check of the expiration date of the reagents.
 * @Author Ayoub Ait Si Ahmad
 */
@EnableScheduling
@SpringBootApplication
public class LabXpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabXpertApplication.class, args);
    }
}
