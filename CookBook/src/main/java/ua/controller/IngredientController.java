package ua.controller;

import static ua.util.ParamBuilder.buildParams;

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

import ua.entity.Ingredient;
import ua.service.IngredientService;
import ua.validator.IngredientValidator;

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
	
	@InitBinder("ingredient")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new IngredientValidator(service));
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("ingredients", service.findAll(pageable));
		return "ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable){
		service.delete(id);
		return "redirect:/admin/ingredient"+buildParams(pageable);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
		model.addAttribute("ingredient", service.findOne(id));
		show(model, pageable);
		return "ingredient";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
		status.setComplete();
		return "redirect:/admin/ingredient"+buildParams(pageable);
	}
	
	@PostMapping
	public String save(@ModelAttribute("ingredient") @Valid Ingredient entity, BindingResult br, SessionStatus status, @PageableDefault Pageable pageable, Model model){
		if(br.hasErrors()){
			show(model, pageable);
			return "ingredient";
		}
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/ingredient"+buildParams(pageable);
	}
}
