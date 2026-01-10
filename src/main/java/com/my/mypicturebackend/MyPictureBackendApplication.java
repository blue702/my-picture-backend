package com.my.mypicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.my.mypicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPictureBackendApplication.class, args);
    }

}
