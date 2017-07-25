package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.domain.request.ApartmentRequest;
import ua.entity.Area;
import ua.entity.RentType;
import ua.entity.Street;
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
		model.addAttribute("rentTypes", rentTypeService.findAll());
		model.addAttribute("areas", areaService.findAll());
		model.addAttribute("streets", streetService.findAll());
		return "apartment";
	}
	
	@PostMapping
	public String add(
					@RequestParam String price,
					@RequestParam Integer rentTypeId,
					@RequestParam String rooms,
					@RequestParam Integer areaId,
					@RequestParam Integer streetId,
					@RequestParam String number,
					@RequestParam String description){
		RentType rentType = rentTypeService.findOne(rentTypeId);
		Street street = streetService.findOne(streetId);
		Area area = areaService.findOne(areaId);
		ApartmentRequest request = new ApartmentRequest(price, rentType, rooms, area, street, number, description);
		service.save(request);
		return "redirect:/apartment";
	}
}
