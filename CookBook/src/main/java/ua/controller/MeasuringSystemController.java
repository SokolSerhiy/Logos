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

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

@Controller
@RequestMapping("/admin/ms")
@SessionAttributes("measuringSystem")
public class MeasuringSystemController {

	@Autowired
	private MeasuringSystemService service;
	
	@ModelAttribute("measuringSystem")
	public MeasuringSystem getForm(){
		return new MeasuringSystem();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("measuringSystems", service.findAll());
		return "ms";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id){
		service.delete(id);
		return "redirect:/admin/ms";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model){
		model.addAttribute("measuringSystem", service.findOne(id));
		show(model);
		return "ms";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/ms";
	}
	
	@PostMapping
	public String save(@ModelAttribute("measuringSystem") MeasuringSystem entity, SessionStatus status){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/ms";
	}
}
