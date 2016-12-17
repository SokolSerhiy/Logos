package ua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		/* Connection це інтерфейс, реалізація якого є у всіх драйверах БД
		 * використовується для того, що б доступитись до БД
		 * якщо об’єкт створився, то звязок БД встановлено і можна працювати
		 * DriverManager.getConnection(String url, String user, String password)
		 * Менеджер реєструє усі драйвери які є в classpath 
		 * метод поветрає Connection за url який по суті для кожної БД різний
		 * також необхідно правильно вказати користувача і пароль
		 * Connection створюється в try-with-resources для того щоб самостійно не закривати**/
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1987")){
			/* Statement інтерфейс для простих запитів без змінних параметрів
			 * підходить для створеня таблиць та створення ключів**/
			Statement st = conn.createStatement();
			/* цей метод дозволяє виконати запит в БД
			 * за параметри приймає стрічку в якій написатий запит в БД**/
			st.execute("CREATE TABLE Person(id int PRIMARY KEY AUTO_INCREMENT, name varchar(255))");
			/* все потрібно закривати**/
			st.close();
			/* PreparedStatement добре підходить для запитів у яких є підстівні параметри**/
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Person(name) values (?)");
			/* цей метод дозволяє встановити підстівний параметр на позицію першого знаку запитання**/
			ps.setString(1, "Ivan");
			/* цей метод краще використовувати коли у вас запит UPDATE, INSERT чи DELETE**/
			ps.executeUpdate();
			/* все потрібно закривати**/
			ps.close();
			/* витягуємо все що є в таблиці Person**/
			ps = conn.prepareStatement("SELECT * FROM Person");
			/* ResultSet інтерфейс який являється ітератором для результатів запиту**/
			ResultSet rs = ps.executeQuery();
			/* цей метод є оночасно і показником чи є ще рядки,
			 * а також перемикачем на наступний рядок**/
			while(rs.next()){
				/* метод який повертає трічку за індексом стовпця
				 * індекси починаються з 1 з ліва
				 * можливий варіант не за індексом, а за назвою стовпця
				 * System.out.println(rs.getString("name"));**/
				System.out.println(rs.getString(2));
			}
		}catch(SQLException e){
		}
	}

}
