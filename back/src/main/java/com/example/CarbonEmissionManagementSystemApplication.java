package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.dao.mapper")
public class CarbonEmissionManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarbonEmissionManagementSystemApplication.class, args);
    }

}
