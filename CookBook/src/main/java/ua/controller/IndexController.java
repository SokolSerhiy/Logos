package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.entity.Amount;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "admin";
	}
	
	@PostMapping("/")
	public String index(@ModelAttribute Amount amount, @RequestParam MultipartFile file){
		System.out.println(file.getOriginalFilename());
		System.out.println(amount.getIngredient().getName());
		System.out.println(amount.getAmount());
		return "redirect:/";
	}
}
