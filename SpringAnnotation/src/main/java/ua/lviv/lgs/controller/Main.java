package ua.lviv.lgs.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.lviv.lgs.service.AuthorService;

public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new
				ClassPathXmlApplicationContext("appContext.xml");
		AuthorService service = (AuthorService) context
				.getBean("authorService");
//		service.insertPerson("Bulgakov", 51);
		System.out.println(service.findByNameAndAge("Bulgakov", 51));
		context.close();

	}

}
