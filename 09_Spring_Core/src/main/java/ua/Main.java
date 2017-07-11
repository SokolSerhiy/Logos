package ua;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.classes.Bard;

public class Main {

	public static void main(String[] args) {
		//Створюємо контейнер бінів, вказуючи де знаходиться конфігураційний файл
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
		//отримуємо бін по класу
		//можна отримати і за id, але тоді спрінг видасть екземпляр
		//Object і потрібно буде робити приведення типів
		//А оскільки бінів строрених по класу бард тільки 1
		//тому отримуємо по класу і викликаємо метод барда
		//що б перевірити чи встановилась балада
		
		Bard bard = context.getBean(Bard.class);
		bard.singing();
		//закриваємо контейнер і знищуємо усі біни
		context.close();
	}
}