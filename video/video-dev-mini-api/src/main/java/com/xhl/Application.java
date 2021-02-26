package com.xhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
//@ComponentScan(basePackages = {"com.xhl"})

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
