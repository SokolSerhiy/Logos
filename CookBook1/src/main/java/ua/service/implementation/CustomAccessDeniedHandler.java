package ua.service.implementation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.sendRedirect("https://ru.wiktionary.org/wiki/%D1%85%D0%B8%D1%82%D1%80%D0%BE%D0%B6%D0%BE%D0%BF%D1%8B%D0%B9");
	}

}
