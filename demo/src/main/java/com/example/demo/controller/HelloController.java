package com.example.demo.controller;

import com.example.demo.entity.feign.FeignCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class HelloController {

	@Autowired
	private FeignCli feignCli;

	@GetMapping("/")
	public String index() {
		return "Greetings from demo!";
	}

	@GetMapping("/feign")
	public String getFeign() {
		return feignCli.feignTest();
	}

}
