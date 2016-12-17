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

import ua.entity.NameOfSpecificationDigital;
import ua.service.NameOfSpecificationDigitalService;

@Controller
@RequestMapping("/admin/nosd")
@SessionAttributes(names="form")
public class NameOfSpecificationDigitalController {

	@Autowired
	private NameOfSpecificationDigitalService digitalService;
	
	@ModelAttribute("form")
	public NameOfSpecificationDigital getForm(){
		return new NameOfSpecificationDigital();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("nosds", digitalService.findAll());
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		digitalService.delete(id);
		return "redirect:/admin/nosd";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("form",digitalService.findOne(id));
		model.addAttribute("nosds", digitalService.findAll());
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("form") NameOfSpecificationDigital form, SessionStatus status){
		digitalService.save(form);
		status.setComplete();
		return "redirect:/admin/nosd";
	}
}
