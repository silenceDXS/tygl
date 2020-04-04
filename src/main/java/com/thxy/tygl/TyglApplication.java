package com.thxy.tygl;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thxy.tygl.mapper")
public class TyglApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyglApplication.class, args);
    }

}
