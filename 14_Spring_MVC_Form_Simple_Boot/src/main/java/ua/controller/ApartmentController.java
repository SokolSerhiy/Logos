package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.ApartmentService;
import ua.service.AreaService;
import ua.service.RentTypeService;
import ua.service.StreetService;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	private final ApartmentService service;
	
	private final RentTypeService rentTypeService;
	
	private final AreaService areaService;
	
	private final StreetService streetService;

	@Autowired
	public ApartmentController(
			ApartmentService service,
			RentTypeService rentTypeService,
			AreaService areaService,
			StreetService streetService) {
		this.service = service;
		this.rentTypeService = rentTypeService;
		this.areaService = areaService;
		this.streetService = streetService;
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("apartments", service.findAllIndex());
		return "apartment";
	}
}
