package com.gg3083.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan({"com.gg3083.tb.controller","com.gg3083.tb.mapper"})
@ComponentScan(basePackages = {"com.gg3083.tb.controller","com.gg3083.tb.service","com.gg3083.tb.mapper","com.gg3083.tb.bean"})
@SpringBootApplication()
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
