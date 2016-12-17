package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

@Controller
@RequestMapping("/admin/ms")
@SessionAttributes(names="form")
public class MeasuringSystemController {

	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@ModelAttribute("form")
	public MeasuringSystem getForm(){
		return new MeasuringSystem();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		return "admin-measuringSystem";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		measuringSystemService.delete(id);
		return "redirect:/admin/ms";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("form", measuringSystemService.findOne(id));
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		return "admin-measuringSystem";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("form") MeasuringSystem form, SessionStatus status){
		measuringSystemService.save(form);
		status.setComplete();
		return "redirect:/admin/ms";
	}
}
