package ua.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Category;
import ua.entity.Item;

public class Main {

	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//коли вам необхідно результат з однієї таблиці відфільтрований за значенням іншої
		//потрібно використовувати команду JOIN вона зпівставить дві таблиці за їх зв'язком
		//ВАЖЛИВО в мові запитів JPQL якою ми користуємось не потрібно писати як співставляти 
		//таблиці, в MySQL це робити обов'язково після оператора ON
		//нижче запит який шукає усі товари в яких котегорія Болт
		//робити JOIN і не використовувати таблицю до якої приєднались ЗАБОРОНЕНО
		//в даному прикладі завантажаться лише товари
		List<Item> items = em.createQuery("SELECT i FROM Item i JOIN i.category c WHERE c.name = :name", Item.class)
				.setParameter("name", "Болт")
				.getResultList();
		//Коли необхідно витягнути з бази дані відразу з двох таблиць потрібно зробити JOIN FETCH
		//при цьому використовувати дані з цієї таблиці в запиті не обов’язково
		//обмеження лише одне неможна робити FETCH до 2 і більше списків
		//також якщо ви робите FETCH до списку потрібно використовувати DISTINCT
		Category category = em.createQuery("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.items WHERE c.id = :id", Category.class)
				.setParameter("id", 1)
				.getSingleResult();
		//цей запит працювати не буде, тут є JOIN проте він ніяк не використовується
		em.createQuery("SELECT DISTINCT c FROM Category c LEFT JOIN c.items WHERE c.id = :id");
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
