package com.person;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.person.mapper")
@SpringBootApplication
public class PersonProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonProjectApplication.class, args);
    }

}
