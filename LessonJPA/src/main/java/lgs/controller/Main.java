package lgs.controller;

import java.awt.print.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.find(Book.class, 1);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
