package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import ua.form.RecipeForm;
import ua.service.AmountService;
import ua.service.CountryService;
import ua.service.RecipeService;
import ua.validator.RecipeValidator;

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
	public RecipeForm getForm(){
		return new RecipeForm();
	}
	
	@InitBinder("recipe")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Amount.class, new AmountEditor(amountService));
	   binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
	   binder.setValidator(new RecipeValidator());
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("recipes", service.findAll(pageable));
		model.addAttribute("amounts", amountService.findAll());
		model.addAttribute("countries", countryService.findAll());
		return "recipe";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable){
		service.delete(id);
		return "redirect:/admin/recipe";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
		model.addAttribute("recipe", service.findOne(id));
		show(model, pageable);
		return "recipe";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
		status.setComplete();
		return "redirect:/admin/recipe";
	}
	
	@PostMapping
	public String save(@ModelAttribute("recipe") @Valid RecipeForm entity,BindingResult br, SessionStatus status, @PageableDefault Pageable pageable, Model model){
		if(br.hasErrors()){
			show(model, pageable);
			return "recipe";
		}
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/recipe";
	}
}
