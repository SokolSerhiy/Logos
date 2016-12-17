package ua.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "user-index";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
}
