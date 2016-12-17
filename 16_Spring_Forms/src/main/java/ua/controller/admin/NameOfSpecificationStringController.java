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

import ua.entity.NameOfSpecificationString;
import ua.service.NameOfSpecificationStringService;

@Controller
@RequestMapping("/admin/noss")
@SessionAttributes(names="form")
public class NameOfSpecificationStringController {

	@Autowired
	private NameOfSpecificationStringService stringService;
	
	@ModelAttribute("form")
	public NameOfSpecificationString getForm(){
		return new NameOfSpecificationString();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("nosss", stringService.findAll());
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("form", stringService.findOne(id));
		model.addAttribute("nosss", stringService.findAll());
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		stringService.delete(id);
		return "redirect:/admin/noss";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("form") NameOfSpecificationString form, SessionStatus status){
		stringService.save(form);
		status.setComplete();
		return "redirect:/admin/noss";
	}
}
