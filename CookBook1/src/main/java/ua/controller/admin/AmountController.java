package ua.controller.admin;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Amount;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;
import ua.service.AmountService;
import ua.service.IngredientService;
import ua.service.MeasuringSystemService;

@Controller
@RequestMapping("/admin/amount")
public class AmountController {

	@Autowired
	private AmountService amountService;
	
	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@Autowired
	private IngredientService ingredientService;
	
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
	
	@PostMapping
	public String save(@RequestParam Long ingredientId,
			@RequestParam Long measuringSystemId,
			@RequestParam BigDecimal amount){
		MeasuringSystem measuringSystem = measuringSystemService.findOne(measuringSystemId);
		Ingredient ingredient = ingredientService.findOne(ingredientId);
		Amount entity = new Amount();
		entity.setAmount(amount);
		entity.setIngredient(ingredient);
		entity.setSystem(measuringSystem);
		amountService.save(entity);
		return "redirect:/admin/amount";
	}
	
}
