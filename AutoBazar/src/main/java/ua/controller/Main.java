package ua.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.entity.Car;
import ua.entity.Engine;
import ua.entity.Fuel;
import ua.entity.Transmission;
import ua.entity.Type;

public class Main {

	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
//		Country country = em
//		.createQuery("SELECT c FROM Vendor v JOIN v.country c WHERE v.name = :name", Country.class)
//		.setParameter("name", "BMW")
//		.getSingleResult();
		List<Car> cars = em
		.createQuery("SELECT c FROM Car c WHERE c.vendor.id = :id", Car.class)
		.setParameter("id", 1).getResultList();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Car> cq = cb.createQuery(Car.class);
		Root<Car> root = cq.from(Car.class);
		cq.select(root);
		
		//пошук за виробником
		List<Integer> ids = new ArrayList<>(Arrays.asList(1,5));
		Predicate vendor = root.get("vendor").in(ids);
		
		//пошук за кольором
		ids = new ArrayList<>(Arrays.asList(3,4));
		Predicate color = root.get("color").in(ids);
		
		//пошук за типом коробки
		Join<Car, Transmission> join = root.join("transmission");
		Predicate type = cb.equal(join.get("type"), Type.AUTOMATIC);
		
		//пошук за пальним
		Join<Car, Engine> engJoin = root.join("engine");
		Join<Engine, Fuel> fJoin = engJoin.join("fuel");
		Predicate fuel = cb.equal(fJoin.get("name"), "disel");
		
		Predicate all = cb.and(vendor, color, type, fuel);
		cq.where(all);
		
		em.createQuery(cq).getResultList();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
