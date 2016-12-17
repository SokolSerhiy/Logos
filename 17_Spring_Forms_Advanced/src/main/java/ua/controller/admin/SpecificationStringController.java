package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.editor.NameOfSpecificationStringEditor;
import ua.entity.NameOfSpecificationString;
import ua.entity.SpecificationString;
import ua.service.NameOfSpecificationStringService;
import ua.service.SpecificationStringService;

@Controller
@RequestMapping("/admin/ss")
@SessionAttributes(names="form")
public class SpecificationStringController {

	@Autowired
	private SpecificationStringService specificationStringService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(NameOfSpecificationString.class, new NameOfSpecificationStringEditor(nameOfSpecificationStringService));
	}
	
	@ModelAttribute("form")
	public SpecificationString getForm(){
		return new SpecificationString();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("specStrings", specificationStringService.findAll());
		model.addAttribute("nosss", nameOfSpecificationStringService.findAll());
		return "admin-specificationString";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("form", specificationStringService.findOne(id));
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
	public String save(@ModelAttribute("form") SpecificationString form, SessionStatus status){
		specificationStringService.save(form);
		status.setComplete();
		return "redirect:/admin/ss";
	}
}
