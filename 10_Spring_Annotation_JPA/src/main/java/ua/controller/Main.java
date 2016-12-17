package ua.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.repository.CategoryRepository;

public class Main {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
		CategoryRepository repository = context.getBean(CategoryRepository.class);
		
		context.close();
	}
}
