package com.w3dai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//这个注解实际上是由“Configuration”，“EnableAutoConfiguration”，“ComponentScan”组成
//ComponentScan注解默认扫描当前包以及该包下面所有的子包
@SpringBootApplication
public class NIRASApplication {
    public static void main(String[] args) {
        SpringApplication.run(NIRASApplication.class, args);
    }

}

