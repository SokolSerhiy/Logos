package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.SpecificationStringService;

@Controller
@RequestMapping("/admin/ss")
public class SpecificationStringController {

	@Autowired
	private SpecificationStringService specificationStringService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("specStrings", specificationStringService.findAll());
		return "admin-specificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		specificationStringService.delete(id);
		return "redirect:/admin/ss";
	}
}
