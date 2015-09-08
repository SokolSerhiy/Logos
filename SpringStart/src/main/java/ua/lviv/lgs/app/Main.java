package ua.lviv.lgs.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;

public class Main {

	public static void main(String[] args) {
		//Context managed the bean`s lifecycle.
		// Об'єкт контексту Спрінга, який керує створенням та життєвим циклом кожного bean.
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"appContext.xml");
		
		AuthorDao dao = (AuthorDao) context.getBean("authorDao");
		dao.insertAuthor(new Author("Tolkin", 25));
		dao.insertAuthor(new Author("King", 56));
		System.out.println(dao.getAllAuthors());
		context.close();

	}

}
