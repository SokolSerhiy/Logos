package ua.controller;

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

import ua.entity.Country;
import ua.service.CountryService;

@Controller
@RequestMapping("/admin/country")
@SessionAttributes("country")
public class CountryController {

	@Autowired
	private CountryService service;
	
	@ModelAttribute("country")
	public Country getForm(){
		return new Country();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("countrys", service.findAll());
		return "country";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		service.delete(id);
		return "redirect:/admin/country";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("country", service.findOne(id));
		show(model);
		return "country";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/country";
	}
	
	@PostMapping
	public String save(@ModelAttribute("country") Country entity, SessionStatus status){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/country";
	}
}