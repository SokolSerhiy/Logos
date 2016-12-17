package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.NameOfSpecificationDigitalService;

@Controller
@RequestMapping("/admin/nosd")
public class NameOfSpecificationDigitalController {

	@Autowired
	private NameOfSpecificationDigitalService digitalService;
	
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
}
