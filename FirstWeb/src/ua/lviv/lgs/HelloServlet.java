package ua.lviv.lgs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet - java class, which should extends HttpServlet class. 
// Сервлет - java клас, який повинен унаслідуватись від класу HttpServlet.

// Servlet is simply a class which responds to a particular type of HTTP request.
// Сервлет є класом, який обробляє методи HTTP запиту.

public class HelloServlet extends HttpServlet{
	
	// HttpServlet provides possibility of overriding HTTP methods (GET, POST etc.)
	// HttpServlet надає можливість перевизначати методи HTTP.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//HttpServletRequest is used for sending data to web page.
		//HttpServletRequest використовується для передачі даних на веб сторінку.

		req.setAttribute("key", new Person("Petro", 54));
		req.getRequestDispatcher("/hello1.jsp").forward(req, resp);
	}
}
