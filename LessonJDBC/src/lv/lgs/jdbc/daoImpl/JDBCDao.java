package lv.lgs.jdbc.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lv.lgs.jdbc.domain.Author;

public class JDBCDao {
	
	//клас обєкт якого отримує зєднання з базою данних
	Connection conn;
	//клас обєкт якого здатен формувати запити до БД
	//винесений як поле класу для того щоб не дублювати оголошення в кожному методі
	//запити сформовані через цей клас можна створити зі змінними параметрами
	PreparedStatement ps;
	
	public JDBCDao() throws SQLException{
		//Connection отримує зєднання з базою данних від класу DriverManager через статичний метод getConnection
		//getConnection приймає за параметри шлях до БД, логін і пароль відповідно до порядку в якому вони написані нижче
		conn = DriverManager.getConnection("jdbc:mysql://localhost/lesson", "root", "1987");
		//клас обєкт якого здатен формувати запити до БД
		//запити сформовані цим класом повинні бути простими без змінних параметрів
		Statement statement = conn.createStatement();
		//execute метод який відправляє запит до БД, в ньому ж і передається сам запит
		//в цьому запиті створюється таблиця яка називається як клас
		//кожен стовпець цієї таблиці відповідає полю класу для якого вона створена
		statement.execute("create table if not exists `author` (`id` int auto_increment primary key, `firstName` varchar(45), `lastName` varchar(45))");
	}
	//метод створює обєкт в БД у вигляді рядка таблиці яка створена вище
	//оскільки стовпці таблиці відображають поля класу то кожен заповнений рядок
	//буде відповідати обєкту для якого створено таблицю
	public void create(Author author) throws SQLException{
		//обєкт PreparedStatement створюється методом prepareStatement класу Connection prepareStatement
		//параметром методу виступає стрічкове представлення sql запиту
		//невідомі параметри позначаються ?
		ps = conn.prepareStatement("insert into `author`(`firstName`, `lastName`) values(?, ?)");
		//встановлення невідомих параметрів відбувається через метод set...класу PreparedStatement
		//де ... тип данних який потрібно підставити в запит
		//параметри методу:
		//1) номер невідомого параметру в sql запиті, з ліва направо нумерація від 1
		//2) значення невідомого параметру в sql запиті
		ps.setString(1, author.getFirsName());
		ps.setString(2, author.getLastName());
		//метод який відправляє запит на сервер
		ps.execute();
	}
	//метод який повертає усі обєкти з БД
	public List<Author> read() throws SQLException{
		//запит який вибирає в таблиці усі рядки
		ps = conn.prepareStatement("select * from `author`");
		//клас ResultSet обєкт якого містить в собі відображеня sql таблиці
		//створюється він при використанні методу executeQuery без параметрів класу PreparedStatement
		//executeQuery метод працює таки чином що sql запит не тільки обробляється на sql сервері,
		//а й повертає певний результат виконання запиту
		ResultSet resSet = ps.executeQuery();
		//Оголошення та ініціалізація ArrayList параметризованого для Author
		List<Author> listAuthors = new ArrayList<Author>();
		//цикл while з умовою що метод next() класу ResultSet буде повертати true
		//цей метод буде повертати true стільки разів скільки є рядкав в таблиці
		//при кожній ітерації в блоці циклу є можливість доступитись до одного рядку таблиці
		while(resSet.next()){
			//оголошення та ініціалізація обєкту Автор
			Author a = new Author();
			//встановлення поля id класу Author методом setId
			//значення яке встановлюється отримано з обєкту ResultSet
			//методом getInt який повертає int-ове значення
			//параметром методу виступає номер стовпця в sql таблиці з ліва на право від 1
			a.setId(resSet.getInt(1));
			//аналогічно попередньому, тільки тут метод getString з параметром 2
			//який відповідає номеру стовпця в таблиці
			a.setFirsName(resSet.getString(2));
			a.setLastName(resSet.getString(3));
			//додавання щойно створеного автора в ліст
			listAuthors.add(a);
		}
		//повернення заповненого ліста
		return listAuthors;
	}
	//метод видаляє рядок за полем id
	public void delete(Author author) throws SQLException{
		ps = conn.prepareStatement("delete from author where `id` = ?");
		ps.setInt(1, author.getId());
		ps.execute();
	}
	
	//метод оновлює данні про обєкт в БД
	//обовязково повинен бути id
	public void update(Author author) throws SQLException{
		ps = conn.prepareStatement("UPDATE `author` SET `firstName` = ?, `lastName` = ? WHERE `id`=?;");
		ps.setInt(3, author.getId());
		ps.setString(1, author.getFirsName());
		ps.setString(2, author.getLastName());
		ps.execute();
	}
}
