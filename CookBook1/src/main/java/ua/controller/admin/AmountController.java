package ua.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.dto.form.AmountForm;
import ua.editor.IngredientEditor;
import ua.editor.MeasuringSystemEditor;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;
import ua.service.AmountService;
import ua.service.IngredientService;
import ua.service.MeasuringSystemService;
import ua.validator.AmountValidator;

@Controller
@RequestMapping("/admin/amount")
@SessionAttributes("amount")
public class AmountController {

	@Autowired
	private AmountService amountService;
	
	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@InitBinder("amount")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Ingredient.class, new IngredientEditor(ingredientService));
		binder.registerCustomEditor(MeasuringSystem.class, new MeasuringSystemEditor(measuringSystemService));
		binder.setValidator(new AmountValidator(amountService));
	}
	
	@ModelAttribute("amount")
	public AmountForm getForm(){
		return new AmountForm();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("amounts", amountService.findAll());
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		model.addAttribute("ingredients", ingredientService.findAll());
		return "admin-amount";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		amountService.delete(id);
		return "redirect:/admin/amount";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("amount", amountService.findForm(id));
		return show(model);
	}
	
	@PostMapping
	public String save(@ModelAttribute("amount") @Valid AmountForm amount, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()) return show(model);
		amountService.save(amount);
		status.setComplete();
		return "redirect:/admin/amount";
	}
	
}
