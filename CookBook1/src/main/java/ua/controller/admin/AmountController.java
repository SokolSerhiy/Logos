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

import ua.dto.filter.AmountFilter;
import ua.dto.form.AmountForm;
import ua.editor.IngredientEditor;
import ua.editor.MeasuringSystemEditor;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;
import ua.service.AmountService;
import ua.service.IngredientService;
import ua.service.MeasuringSystemService;
import ua.util.ParamBuilder;
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
	
	@ModelAttribute("filter")
	public AmountFilter getFilter(){
		return new AmountFilter();
	}
	
	@ModelAttribute("amount")
	public AmountForm getForm(){
		return new AmountForm();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") AmountFilter filter){
		model.addAttribute("page", amountService.findAll(pageable, filter));
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		model.addAttribute("ingredients", ingredientService.findAll());
		return "admin-amount";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") AmountFilter filter){
		amountService.delete(id);
		return "redirect:/admin/amount"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") AmountFilter filter){
		model.addAttribute("amount", amountService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("amount") @Valid AmountForm amount, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") AmountFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		amountService.save(amount);
		status.setComplete();
		return "redirect:/admin/amount"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, AmountFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			buffer.append("&max=");
			buffer.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			buffer.append("&min=");
			buffer.append(filter.getMin());
		}
		if(!filter.getIngredientIds().isEmpty()){
			for (Long id : filter.getIngredientIds()) {
				buffer.append("&ingredientIds=");
				buffer.append(id);
			}
		}
		if(!filter.getSystemIds().isEmpty()){
			for (Long id : filter.getSystemIds()) {
				buffer.append("&systemIds=");
				buffer.append(id);
			}
		}
		return buffer.toString();
	}
	
}
