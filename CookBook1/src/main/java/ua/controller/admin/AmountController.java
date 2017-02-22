package ua.controller.admin;

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
	private AmountService amountService;
	
	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@InitBinder("amount")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Ingredient.class, new IngredientEditor(ingredientService));
		binder.registerCustomEditor(MeasuringSystem.class, new MeasuringSystemEditor(measuringSystemService));
	}
	
	@ModelAttribute("amount")
	public Amount getForm(){
		return new Amount();
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
		model.addAttribute("amount", amountService.findOne(id));
		return show(model);
	}
	
	@PostMapping
	public String save(@ModelAttribute("amount") Amount amount){
		amountService.save(amount);
		return "redirect:/admin/amount";
	}
	
}
