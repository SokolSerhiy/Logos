package ua.controller;

import static ua.util.ParamBuilder.buildParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import ua.editor.IngredientEditor;
import ua.editor.MeasuringSystemEditor;
import ua.entity.Amount;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;
import ua.service.AmountService;
import ua.service.IngredientService;
import ua.service.MeasuringSystemService;

@Controller
@RequestMapping("/admin/amount")
@SessionAttributes("amount")
public class AmountController {

	@Autowired
	private AmountService service;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@InitBinder("amount")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Ingredient.class, new IngredientEditor(ingredientService));
	   binder.registerCustomEditor(MeasuringSystem.class, new MeasuringSystemEditor(measuringSystemService));
	}
	
	@ModelAttribute("amount")
	public Amount getForm(){
		return new Amount();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("amounts", service.findAll(pageable));
		model.addAttribute("ingredients", ingredientService.findAll());
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		return "amount";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable){
		service.delete(id);
		return "redirect:/admin/amount"+buildParams(pageable);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
		model.addAttribute("amount", service.findOne(id));
		show(model, pageable);
		return "amount";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
		status.setComplete();
		return "redirect:/admin/amount"+buildParams(pageable);
	}
	
	@PostMapping
	public String save(@ModelAttribute("amount") Amount entity, SessionStatus status, @PageableDefault Pageable pageable){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/amount"+buildParams(pageable);
	}
}
