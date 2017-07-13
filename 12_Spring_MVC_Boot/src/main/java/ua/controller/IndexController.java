package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String welcome(){
		return "index";
	}
	
	@GetMapping
	public String admin(){
		return "admin";
	}
}
