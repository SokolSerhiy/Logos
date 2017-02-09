package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.BasicFilter;
import ua.editor.NameOfSpecificationStringEditor;
import ua.entity.NameOfSpecificationString;
import ua.entity.SpecificationString;
import ua.service.NameOfSpecificationStringService;
import ua.service.SpecificationStringService;
import ua.validator.SpecificationStringValidator;

@Controller
@RequestMapping("/admin/ss")
@SessionAttributes(names="ss")
public class SpecificationStringController {

	@Autowired
	private SpecificationStringService specificationStringService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@InitBinder("ss")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(NameOfSpecificationString.class, new NameOfSpecificationStringEditor(nameOfSpecificationStringService));
	   binder.setValidator(new SpecificationStringValidator(specificationStringService));
	}
	
	@ModelAttribute("ss")
	public SpecificationString getForm(){
		return new SpecificationString();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", specificationStringService.findAll(filter, pageable));
		model.addAttribute("nosss", nameOfSpecificationStringService.findAll());
		return "admin-specificationString";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("ss", specificationStringService.findOne(id));
		model.addAttribute("page", specificationStringService.findAll(filter, pageable));
		model.addAttribute("nosss", nameOfSpecificationStringService.findAll());
		return "admin-specificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		specificationStringService.delete(id);
		return "redirect:/admin/ss"+getParams(pageable, filter);
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("ss")@Valid SpecificationString form,BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", specificationStringService.findAll(filter, pageable));
			model.addAttribute("nosss", nameOfSpecificationStringService.findAll());
			return "admin-specificationString";
		}
		specificationStringService.save(form);
		status.setComplete();
		return "redirect:/admin/ss"+getParams(pageable, filter);
	}
}
