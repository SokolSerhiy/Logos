package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import ua.service.NameOfSpecificationStringService;
import ua.service.SpecificationStringService;

@Controller
@RequestMapping("/admin/ss")
public class SpecificationStringController {

	@Autowired
	private SpecificationStringService specificationStringService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("specStrings", specificationStringService.findAll());
		model.addAttribute("nosss", nameOfSpecificationStringService.findAll());
		return "admin-specificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		specificationStringService.delete(id);
		return "redirect:/admin/ss";
	}
	
	@RequestMapping(method=POST)
	public String save(@RequestParam String name, @RequestParam int nossId){
		specificationStringService.save(name, nossId);
		return "redirect:/admin/ss";
	}
}
