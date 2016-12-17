package ua.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Category;
import ua.entity.Item;
import ua.entity.Producer;
import ua.entity.SpecificationDigital;

public class Main {

	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//Запит який дозволяє знайти катогорії за її назвою
		//FROM Category c - тут як в джава, оголошення змінної, тобто с в запиті буде Category
		//SELECT c - показує що ми отримаємо всю категорію повністю з всімя її стовпцями
		//WHERE c.name=:name - умова пошуку, де назва категорії відповідає параметру 
		//який буде підставлений в процесі виконання
		// : означає що далі іменований підставний параметр після двокрапки до першого пробілу
		Category category = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
		//встановлює значення іменованого параметру, назва параметру без двокрапки
		//Болт може бути змінною
				.setParameter("name", "Болт")
		//цей метод означає що ми хочемо отримати один результат, обережно, якщо не знайде нічого
		//видасть NoResultException, якщо знайде більше одного - NonUniqueResultException
				.getSingleResult();
		//LIKE оператор неточного пошуку % - заміняє будь-яку кількість будь-яких символів
		// _ - заміняє один символ, в запиті що написаний нижче шукає всі товари які починаються з букви Б
		//НІКОЛИ не ставте % перед буквами
		//оператори > < <= >= виконують ті ж функції що і в джава
		//вирази можна поєднувати операторами AND і OR, також заперечення виразу потрібно писати через NOT
		List<Item> items = em.createQuery("SELECT i FROM Item i WHERE i.name LIKE :name AND i.price > :price", Item.class)
		//цих методів може бути стільки стільки підставних параметрів в запиті
				.setParameter("name", "Б%")
				.setParameter("price", new BigDecimal("3.20"))
				//формує список з результатів
				.getResultList();
		//IN - це заміна багатьох або, підставний параметр має бути список відповідного типу діних
		List<Producer> producers = em.createQuery("SELECT p FROM Producer p WHERE p.id IN (:ids)", Producer.class)
				.setParameter("ids", Arrays.asList(1,2,3,4,7,9))
				.getResultList();
		
		List<SpecificationDigital> specificationDigitals = 
				//просто шукає в діапазоні
				em.createQuery("SELECT sd FROM SpecificationDigital sd WHERE sd.value BETWEEN :first AND :second", SpecificationDigital.class)
				.setParameter("first", new BigDecimal("3.20"))
				.setParameter("second", new BigDecimal("6.50"))
				.getResultList();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
