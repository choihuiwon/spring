package com.korea.Springdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	@Bean
	public Greeting greeter() {
		Greeting g = new Greeting(1000, "di_test");
		return g;
	}
	
	@Bean
	public Person person() {
		return new Person("최희원", 25);
	}
}
