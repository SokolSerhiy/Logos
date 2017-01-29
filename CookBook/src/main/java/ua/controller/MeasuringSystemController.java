package ua.controller;

import static ua.util.ParamBuilder.buildParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("measuringSystems", service.findAll(pageable));
		return "ms";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable){
		service.delete(id);
		return "redirect:/admin/ms"+buildParams(pageable);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
		model.addAttribute("measuringSystem", service.findOne(id));
		show(model, pageable);
		return "ms";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
		status.setComplete();
		return "redirect:/admin/ms"+buildParams(pageable);
	}
	
	@PostMapping
	public String save(@ModelAttribute("measuringSystem") MeasuringSystem entity, SessionStatus status, @PageableDefault Pageable pageable){
		service.save(entity);
		status.setComplete();
		return "redirect:/admin/ms"+buildParams(pageable);
	}
}
