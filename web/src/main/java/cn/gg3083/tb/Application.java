package cn.gg3083.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan({"cn.gg3083.tb.controller","cn.gg3083.tb.mapper"})
@ComponentScan(basePackages = {"cn.gg3083.tb.controller","cn.gg3083.tb.service","cn.gg3083.tb.mapper","cn.gg3083.tb.bean"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
