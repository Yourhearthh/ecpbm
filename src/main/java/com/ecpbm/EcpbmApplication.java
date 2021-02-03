package com.ecpbm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ecpbm.dao.mapper")
public class EcpbmApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcpbmApplication.class, args);
    }

}
