package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.service.ApartmentService;

@Controller
public class IndexController {
	
	private final ApartmentService service;
	
	@Autowired
	public IndexController(ApartmentService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String welcome(Model model){
		model.addAttribute("apartments", service.findTop5ByRate());
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "admin";
	}
}
