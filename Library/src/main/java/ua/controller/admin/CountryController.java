package ua.controller.admin;

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
	private CountryService countryService;
	
	@ModelAttribute("country")
	public Country getForm(){
		return new Country();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("countries", countryService.findAll());
		return "admin-country";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		countryService.delete(id);
		return "redirect:/admin/country";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		show(model);
		model.addAttribute("country", countryService.findOne(id));
		return "admin-country";
	}
	@PostMapping
	public String save(@ModelAttribute("country") Country country, SessionStatus status){
		countryService.save(country);
		status.setComplete();
		return "redirect:/admin/country";
	}
}
