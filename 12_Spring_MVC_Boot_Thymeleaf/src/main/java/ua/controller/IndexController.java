package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String welcome(Model model){
		model.addAttribute("welcome", "Welcome!");
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "admin";
	}
}
