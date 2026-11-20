package com.vineeth.learnings.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vineeth.learnings")		//necessary as this package is inside the another package
public class SpringBootLangChain4JApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootLangChain4JApplication.class, args);
	}

}
