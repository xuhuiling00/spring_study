package com.xhl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试是否搭建成果
 * 2021.01.20
 */
@RestController
public class HelloWorldController {
	
	@RequestMapping("/hello")
	public String Hello() {
		return "Hello Spring Boot~";
	}
	@RequestMapping("/")
	public String index() {
		return "Welcome to use video-api";
	}
	
}
