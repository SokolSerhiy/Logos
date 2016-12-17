package ua;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FormServlet extends HttpServlet{
	
	private static final long serialVersionUID = 4656205184818860704L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//методом GET показуюємо реєстрацію
		req.getRequestDispatcher("/registration.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//методом POST отримуємо параметри з форми яку заповнив користувач
		//getParameter дозволяє за назвою параметра отримати значення
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		//створюємо користувача
		User user = new User(login, password);
		//отримуємо сесію
		HttpSession session = req.getSession();
		//передаємо в сесію користувача
		session.setAttribute("user", user);
		//після того як отримали все що потрібно з запиту
		//робимо редірект на посилання /
		//після методу POST в абсолютній більшості випадків
		//потрібно робити редірект кудись
		//це перенаправить користувача методом GET
		resp.sendRedirect("/");
	}
}