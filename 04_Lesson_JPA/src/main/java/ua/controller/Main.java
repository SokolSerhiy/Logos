package ua.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Category;

public class Main {

	public static void main(String[] args) {
		//та сама одиниця з persistence.xml
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		//основний інтерфейс для роботи з БД
		final EntityManager em = factory.createEntityManager();
		//почати транзакцію
		em.getTransaction().begin();
		Category category = new Category();
		//зберегти
		em.persist(category);
		//оновити
		em.merge(category);
		//знайти по id
		Category category2 = em.find(Category.class, 1);
		//видалити
		em.remove(category);
		//закінчити транзакцію
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
