package ua;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//Наслідуємось від класу який має заглушки на все HTTP методи
public class IndexServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1321557318440281164L;

	@Override
	//перевизначаємо той метод по якому буде працювати сервлет
	//в даному випадку працювати буде по методу GET
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//отримуємо сесію з запиту
		//сесія завжди створюється для кожного користувача
		//в сесію можна сховати будь-який обєкт який серіалізується
		HttpSession session = req.getSession();
		//додаємо в запит атрибут user з сесії
		//якщо в сесії його немає буде null
		req.setAttribute("user", session.getAttribute("user"));
		//для побудови представлення викликаємо index.jsp
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	
}
