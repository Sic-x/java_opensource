package com.xmh.springboot;


import org.springframework.web.bind.annotation.*;

@RestController

public class Example {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}



}