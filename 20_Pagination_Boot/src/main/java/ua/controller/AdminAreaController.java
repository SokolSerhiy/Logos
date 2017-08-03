package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.entity.Area;
import ua.service.AreaService;

@Controller
@RequestMapping("/admin/area")
@SessionAttributes("area")
public class AdminAreaController {
	
	private final AreaService service;
	
	@Autowired
	public AdminAreaController(AreaService service) {
		this.service = service;
	}
	
	@ModelAttribute("area")
	public Area getForm(){
		return new Area();
	}

	@GetMapping
	public String show(Model model){
		model.addAttribute("areas", service.findAll());
		return "area";
	}
	
	@PostMapping
	public String add(@ModelAttribute("area") @Valid Area area, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()){
			show(model);
			return "area";
		}
		service.save(area);
		status.setComplete();
		return "redirect:/admin/area";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model){
		model.addAttribute("area", service.findOne(id));
		show(model);
		return "area";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		service.delete(id);
		return "redirect:/admin/area";
	}
	
}
