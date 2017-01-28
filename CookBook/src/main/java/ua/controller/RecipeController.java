package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.editor.AmountEditor;
import ua.editor.CountryEditor;
import ua.entity.Amount;
import ua.entity.Country;
import ua.entity.Recipe;
import ua.service.AmountService;
import ua.service.CountryService;
import ua.service.RecipeService;

@Controller
@RequestMapping("/admin/recipe")
@SessionAttributes("recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;
	
	@Autowired
	private AmountService amountService;
	
	@Autowired
	private CountryService countryService;
	
	@ModelAttribute("recipe")
	public Recipe getForm(){
		return new Recipe();
	}
	
	@InitBinder("recipe")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Amount.class, new AmountEditor(amountService));
	   binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("recipes", service.findAll());
		model.addAttribute("amounts", amountService.findAll());
		model.addAttribute("countries", countryService.findAll());
		return "recipe";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		service.delete(id);
		return "redirect:/admin/recipe";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("recipe", service.findOne(id));
		show(model);
		return "recipe";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/recipe";
	}
	
	@PostMapping
	public String save(@ModelAttribute("recipe") Recipe entity, SessionStatus status){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/recipe";
	}
}
