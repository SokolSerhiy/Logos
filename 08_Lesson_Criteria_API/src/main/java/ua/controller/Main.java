package ua.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.entity.Category;
import ua.entity.Item;
import ua.entity.SpecificationString;

public class Main {

	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//Об’єкт який дозволяє будувати динамічні запити
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Об’єкт запиту параметризований під тип данних що 
		//буде повернено в результаті виконання
		CriteriaQuery<Item> query = cb.createQuery(Item.class);
		//Об’єкт який являється блоком FROM в запиті
		//Ця частина ідентична частині запиту на JPQL 
		//FROM Item i 
		Root<Item> root = query.from(Item.class);
		//Це звичайний селект, ідентично SELECT i 
		query.select(root);
		//Після стрічки вище маємо ось такий запит
		//SELECT i FROM Item i -- який поверне всі товари з БД
		//Тепер потрібно створити умови по яким ми будемо 
		//фільтрувати товари
		//Expression - це об’єкт виразу в блоці WHERE
		//Даний вираз означає наступне i.price
		Expression<BigDecimal> priceExp = root.get("price");
		//Predicate - це вже умова пошуку
		//Є декілька варіантів як її створити
		//більшість цих варіантів являється методами CriteriaBuilder
		//цей метод робить наступну умову less than or equal
		//тепер у нас є умова WHERE i.price <= 10.0
		Predicate pricePredicate = cb.le(priceExp, new BigDecimal("10.0"));
		//Якщо умова вимагає пошуку в інших таблицях
		//потрібно зробити JOIN
		//Як і все в Criteria API це також об’єкт
		//який можна отримати викликавши метод .join(String field)
		//від об’єкту Root
		//цим ми змінили блок FROM до такого вигляду
		//FROM Item i JOIN i.category c
		Join<Item, Category> catJoin = root.join("category");
		//Це вираз WHERE c.name like 'Б%'
		Expression<String> catNameExp = catJoin.get("name");
		Predicate catPredicate = cb.like(catNameExp, "Б%");
		//Після цього блок FROM виглядає так:
		//FROM Item i JOIN i.category c JOIN i.specificationStrings ss
		//а умова так WHERE ss.id in (1,5,20)
		Join<Item, SpecificationString> specStringJoin = root.join("specificationStrings");
		Predicate specStringPredicate = specStringJoin.get("id").in(Arrays.asList(1,5,20));
		//Тепер в нас є три умови:
		//WHERE i.price <= 10.0
		//WHERE c.name like 'Б%'
		//WHERE ss.id in (1,5,20)
		//а потрібно все в одному блоці з оператором AND
		//CriteriaBuilder знову має метод який 
		//підходить для даної затачі
		//він приймає будь-яку кількість об’єктів Predicate
		//передаємо туди всі що є
		//отримаємо один десь такий:
		//WHERE i.price <= 10.0 AND c.name like 'Б%' AND ss.id in (1,5,20)
		Predicate all = cb.and(pricePredicate, catPredicate, specStringPredicate);
		//додаємо цей блок до запиту
		query.where(all);
		//JOIN і FETCH в Criteria API це різні речі
		//і якщо ви хочете зробити fetch
		//потрібно його викликати окремо не зважаючи на
		//всі інші JOIN
		//тепер блок FROM виглядає так:
		//FROM Item i JOIN FETCH i.category c JOIN i.specificationStrings ss
		root.fetch("category");
		//після цього так:
		//FROM Item i JOIN FETCH i.category c JOIN
		// i.specificationStrings ss JOIN FETCH i.measuringSystems
		root.fetch("measuringSystems");
		List<Item> items = em.createQuery(query).getResultList();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}