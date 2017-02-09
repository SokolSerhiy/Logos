package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//вказуємо що це контролер
@Controller
public class IndexController {
	//цей метод буде працювати 
	//як тільки користувач зайде на сайт
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("message", "Hello");
		//потрібно показати index.jsp
		return "index";
	}
	//а цей тоді коли користувач 
	//натисне на писилання /admin
	@RequestMapping("/admin")
	public String admin(){
		//тут admin.jsp
		return "admin";
	}
}
