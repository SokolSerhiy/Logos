package ua.controller.admin;

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

import ua.dto.filter.SimpleFilter;
import ua.entity.Ingredient;
import ua.service.IngredientService;
import ua.validator.IngredientValidator;
import static ua.util.ParamBuilder.*;
@Controller
@RequestMapping("/admin/ingredient")
@SessionAttributes("ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@InitBinder("ingredient")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new IngredientValidator(ingredientService));
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@ModelAttribute("ingredient")
	public Ingredient getForm(){
		return new Ingredient();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", ingredientService.findAll(pageable, filter));
		return "admin-ingredient";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("ingredient", ingredientService.findOne(id));
		show(model, pageable, filter);
		return "admin-ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		ingredientService.delete(id);
		return "redirect:/admin/ingredient"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("ingredient") @Valid Ingredient ingredient, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()){
			return show(model, pageable, filter);
		}
		ingredientService.save(ingredient);
		status.setComplete();
		return "redirect:/admin/ingredient"+getParams(pageable, filter);
	}
}
