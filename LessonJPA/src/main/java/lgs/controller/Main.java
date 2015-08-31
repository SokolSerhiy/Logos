package lgs.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//в resources є ще папка, там повинні бути настройки, називаються persistence.xml
		//тут пробувати зберігати
		//використовувати методи persist, merge, find, remove
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
