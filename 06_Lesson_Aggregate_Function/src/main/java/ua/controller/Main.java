package ua.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//max - повертає максимальне значення в стовпці
		BigDecimal max = em.createQuery("SELECT max(i.price) FROM Item i", BigDecimal.class).getSingleResult();
		//min - повертає мінімальне
		BigDecimal min = em.createQuery("SELECT min(i.price) FROM Item i", BigDecimal.class).getSingleResult();
		//avg - повертає середнє арефметичне значення тип даних завжди Double 
		Double avg = em.createQuery("SELECT avg(i.price) FROM Item i", Double.class).getSingleResult();
		//sum - повертає суму всіх записів в стовпці
		BigDecimal sum = em.createQuery("SELECT sum(i.price) FROM Item i", BigDecimal.class).getSingleResult();
		//count - повертає кількість записів тип даних завжди Long
		Long count = em.createQuery("SELECT count(i.id) FROM Item i", Long.class).getSingleResult();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
