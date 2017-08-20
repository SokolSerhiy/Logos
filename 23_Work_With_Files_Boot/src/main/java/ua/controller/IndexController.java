package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.domain.filter.ApartmentFilter;
import ua.domain.view.ApartmentIndex;
import ua.service.ApartmentService;

@Controller
public class IndexController {
	
	private final ApartmentService service;
	
	@Autowired
	public IndexController(ApartmentService service) {
		this.service = service;
	}
	
	@ModelAttribute("filter")
	public ApartmentFilter getFilter() {
		return new ApartmentFilter();
	}

	@GetMapping("/")
	public String welcome(Model model, @ModelAttribute("filter") ApartmentFilter filter, @PageableDefault Pageable pageable){
		model.addAttribute("areas", service.findAreaNames());
		model.addAttribute("rentTypes", service.findRentTypeNames());
		if(filter.isValid()) {
			Page<ApartmentIndex> page = service.findByFilter(filter, pageable);
			model.addAttribute("apartments", page.getContent());
			model.addAttribute("page", page);
		}else {
			model.addAttribute("apartments", service.findTop5ByRate());
		}
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(){
		return "admin";
	}
}
