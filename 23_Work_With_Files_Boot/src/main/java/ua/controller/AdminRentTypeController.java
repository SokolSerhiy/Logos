package ua.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.RentType;
import ua.service.RentTypeService;

@Controller
@RequestMapping("/admin/renttype")
@SessionAttributes("renttype")
public class AdminRentTypeController {

	private final RentTypeService service;

	public AdminRentTypeController(RentTypeService service) {
		this.service = service;
	}
	
	@ModelAttribute("renttype")
	public RentType getForm(){
		return new RentType();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("rentTypes", service.findAll());
		return "rentType";
	}
	
	@PostMapping
	public String add(@ModelAttribute("renttype") @Valid RentType rentType, BindingResult br, SessionStatus status, Model model){
		if(br.hasErrors()){
			model.addAttribute("rentTypes", service.findAll());
			return "rentType";
		}
		service.save(rentType);
		status.setComplete();
		return "redirect:/admin/renttype";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model){
		model.addAttribute("renttype", service.findOne(id));
		show(model);
		return "rentType";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		service.delete(id);
		return "redirect:/admin/renttype";
	}
	
}
