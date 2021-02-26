package com.xhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan(basePackages = "com.xhl.mapper")
@ComponentScan(basePackages = {"com.xhl","org.n3r.idworker"})

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
