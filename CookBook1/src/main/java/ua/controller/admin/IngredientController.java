package ua.controller.admin;

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
	private IngredientService ingredientService;
	
	@ModelAttribute("ingredient")
	public Ingredient getForm(){
		return new Ingredient();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("ingredients", ingredientService.findAll());
		return "admin-ingredient";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("ingredient", ingredientService.findOne(id));
		show(model);
		return "admin-ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		ingredientService.delete(id);
		return "redirect:/admin/ingredient";
	}
	
	@PostMapping
	public String save(@ModelAttribute("ingredient")Ingredient ingredient, SessionStatus status){
		ingredientService.save(ingredient);
		status.setComplete();
		return "redirect:/admin/ingredient";
	}
}
