package com.etammag.ieat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.etammag.ieat.mapper"})
@EnableTransactionManagement
public class IEatApplication {

    public static void main(String[] args) {
        SpringApplication.run(IEatApplication.class, args);
    }

}
