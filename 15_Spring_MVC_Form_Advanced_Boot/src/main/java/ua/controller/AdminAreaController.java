package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Area;
import ua.service.AreaService;

@Controller
@RequestMapping("/admin/area")
public class AdminAreaController {
	
	private final AreaService service;
	
	@Autowired
	public AdminAreaController(AreaService service) {
		this.service = service;
	}

	@GetMapping
	public String show(Model model){
		model.addAttribute("areas", service.findAll());
		return "area";
	}
	
	@PostMapping
	public String add(@RequestParam String name){
		service.save(new Area(name));
		return "redirect:/admin/area";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		service.delete(id);
		return "redirect:/admin/area";
	}
	
}
