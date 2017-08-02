package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.domain.request.ApartmentRequest;
import ua.service.ApartmentService;

@Controller
@RequestMapping("/apartment")
@SessionAttributes("apartment")
public class ApartmentController {

	private final ApartmentService service;
	
	@Autowired
	public ApartmentController(ApartmentService service) {
		this.service = service;
	}
	
//	@InitBinder("apartment")
//	protected void bind(WebDataBinder binder){
//		binder.registerCustomEditor(RentType.class, new RentTypeEditor(rentTypeService));
//		binder.registerCustomEditor(Street.class, new StreetEditor(streetService));
//		binder.registerCustomEditor(Area.class, new AreaEditor(areaService));
//	}
	
	@ModelAttribute("apartment")
	public ApartmentRequest getForm(){
		return new ApartmentRequest();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("apartments", service.findAllIndex());
		model.addAttribute("rentTypes", service.findRentTypeNames());
		model.addAttribute("areas", service.findAreaNames());
		model.addAttribute("streets", service.findStreetNames());
		return "apartment";
	}
	
	@PostMapping
	public String add(@ModelAttribute("apartment") ApartmentRequest request, SessionStatus status){
		service.save(request);
		status.setComplete();
		return "redirect:/apartment";
	}
}
