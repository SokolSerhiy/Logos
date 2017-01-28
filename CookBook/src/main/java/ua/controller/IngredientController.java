package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Ingredient;
import ua.service.IngredientService;

@Controller
@RequestMapping("/admin/ingredient")
@SessionAttributes("ingredient")
public class IngredientController {

	@Autowired
	private IngredientService service;
	
	@ModelAttribute("ingredient")
	public Ingredient getForm(){
		return new Ingredient();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("ingredients", service.findAll());
		return "ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		service.delete(id);
		return "redirect:/admin/ingredient";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("ingredient", service.findOne(id));
		show(model);
		return "ingredient";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/ingredient";
	}
	
	@PostMapping
	public String save(@ModelAttribute("ingredient") Ingredient entity, SessionStatus status){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/ingredient";
	}
}
