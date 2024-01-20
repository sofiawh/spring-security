package com.simplon.labxpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@ComponentScan(basePackages = {"com.simplon.labxpert", "com.simplon.labxpert.config"})
public class LabXpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabXpertApplication.class, args);
    }

}
