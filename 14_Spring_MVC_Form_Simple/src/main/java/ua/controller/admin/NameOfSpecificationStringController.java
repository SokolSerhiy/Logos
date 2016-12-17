package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.NameOfSpecificationStringService;

@Controller
@RequestMapping("/admin/noss")
public class NameOfSpecificationStringController {

	@Autowired
	private NameOfSpecificationStringService stringService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("nosss", stringService.findAll());
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		stringService.delete(id);
		return "redirect:/admin/noss";
	}
	
	@RequestMapping(method=POST)
	public String save(@RequestParam String name){
		stringService.save(name);
		return "redirect:/admin/noss";
	}
}
