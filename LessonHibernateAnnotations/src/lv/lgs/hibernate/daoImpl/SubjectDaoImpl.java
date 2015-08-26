package lv.lgs.hibernate.daoImpl;

import java.util.List;

import lv.lgs.hibernate.domain.Subject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SubjectDaoImpl {
			//кожен клас який відповідає за роботу з БД повинен оперувати лише об`єктами одного класу
			//полем класу є об`єкт класу SessionFactory
			//цей об`єкт може створювати сесії для роботи з БД
			//не видаляється garbage collector-ом при закінчені роботи основного потоку в мейн методі
			//тому після створення об`єкту цього класу та завершені роботи з ним необхідно викликати метод closeSessionFactory()
			SessionFactory sessionFactory;
			
			//пустий конструктор в якому ініціалізується об`єкт SessionFactory
			public SubjectDaoImpl() {
				//об`єкт Configuration зчитує hibernate.cfg.xml та відразу шукає усі файли з описанням мапінгу
				Configuration configuration = new Configuration().configure();
				//реєструє дані отримані від Configuration
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
						configuration.getProperties()).build();
				//об`єкт Configuration повертає об`єкт SessionFactory за допомогою методу buildSessionFactory()
				//з параметром у вигляді об`єкту ServiceRegistry
				//створення таблиць у БД відбувається під час виконання цієї стрічки коду
				sessionFactory = new Configuration().configure().buildSessionFactory(
						serviceRegistry);
			}
			
			//метод який закриває SessionFactory
			public void closeSessionFactory(){
				sessionFactory.close();
			}
			
			//метод який зберігає об`єкт в БД
			//ссилка на об`єкт є параметром методу
			//важливо зберігати послідовність зберігання об`єктів
			//не можна зберігати об`єкт полем якого є інший об`єкт
			//який ще не збережений до БД
			public void save(Subject subject){
				//отримується об`єкт Session від SessionFactory
				Session ses = sessionFactory.openSession();
				//доступ до транзакції та її відкриття
				ses.beginTransaction();
				//збереження обєкту
				ses.save(subject);
				//доступ до транзакції та її виконання
				ses.getTransaction().commit();
				//закриття сесії
				ses.close();
			}
			
			//метод який оновлює усі поля об`єкту, крім id
			//без id генерує помилку
			public void update(Subject subject){
				Session ses = sessionFactory.openSession();
				ses.beginTransaction();
				ses.update(subject);
				ses.getTransaction().commit();
				ses.close();
			}
			
			//метод який видаляє об`єкт з БД
			//без id нічого не робить
			public void delete(Subject subject){
				Session ses = sessionFactory.openSession();
				ses.beginTransaction();
				ses.delete(subject);
				ses.getTransaction().commit();
				ses.close();
			}
			
			//метод який повертає усі об`єкти з БД
			//unchecked тому що java не може точно перевірити що повернеться з БД
			@SuppressWarnings("unchecked")
			public List<Subject> getAll(){
				Session ses = sessionFactory.openSession();
				ses.beginTransaction();
				List<Subject> subjects;
				subjects = ses.createCriteria(Subject.class).list();
				ses.getTransaction().commit();
				ses.close();
				return subjects;
			}
}
