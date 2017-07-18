package ua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.RentType;
import ua.service.RentTypeService;

@Controller
@RequestMapping("/admin/renttype")
public class AdminRentTypeController {

	private final RentTypeService service;

	public AdminRentTypeController(RentTypeService service) {
		this.service = service;
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("rentTypes", service.findAll());
		return "rentType";
	}
	
	@PostMapping
	public String add(@RequestParam String name){
		service.save(new RentType(name));
		return "redirect:/admin/renttype";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		service.delete(id);
		return "redirect:/admin/renttype";
	}
	
}
