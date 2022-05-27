package com.generation.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que a classe abaixo é uma classe controladora
@RequestMapping("/hello") //serve para a construção dos endpoints da API
public class HelloWorld {
		
		@GetMapping
		public String hello() {
			return "Lista de habilidades e mentalidades da generation";
		}
}
