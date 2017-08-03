package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Street;
import ua.service.StreetService;

@Controller
@RequestMapping("/admin/street")
public class AdminStreetController {

	private final StreetService service;
	
	@Autowired
	public AdminStreetController(StreetService service) {
		this.service = service;
	}

	@GetMapping
	public String show(Model model){
		model.addAttribute("streets", service.findAll());
		return "street";
	}
	
	@PostMapping
	public String add(@RequestParam String name){
		service.save(new Street(name));
		return "redirect:/admin/street";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		service.delete(id);
		return "redirect:/admin/street";
	}
}
