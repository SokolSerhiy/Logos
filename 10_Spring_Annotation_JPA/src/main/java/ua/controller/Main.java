package ua.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.service.CategoryService;

public class Main {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
		CategoryService service = context.getBean(CategoryService.class);
		
		context.close();
	}
}
