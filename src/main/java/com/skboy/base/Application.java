package com.skboy.base;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tangguangwei
 */
@SpringBootApplication
@MapperScan(basePackages = "com.skboy.base.mapper")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
