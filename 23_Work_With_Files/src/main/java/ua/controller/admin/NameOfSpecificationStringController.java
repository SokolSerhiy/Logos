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
import ua.entity.NameOfSpecificationString;
import ua.service.NameOfSpecificationStringService;
import ua.validator.NameOfSpecificationStringValidator;

@Controller
@RequestMapping("/admin/noss")
@SessionAttributes(names="noss")
public class NameOfSpecificationStringController {

	@Autowired
	private NameOfSpecificationStringService stringService;
	
	@InitBinder("noss")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameOfSpecificationStringValidator(stringService));
	}
	
	@ModelAttribute("noss")
	public NameOfSpecificationString getForm(){
		return new NameOfSpecificationString();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	
	@RequestMapping
	public String show(SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", stringService.findAll(filter, pageable));
		status.setComplete();
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("noss", stringService.findOne(id));
		model.addAttribute("page", stringService.findAll(filter, pageable));
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		stringService.delete(id);
		return "redirect:/admin/noss"+getParams(pageable, filter);
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("noss")@Valid NameOfSpecificationString form,BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", stringService.findAll(filter, pageable));
			return "admin-nameOfSpecificationString";
		}
		stringService.save(form);
		status.setComplete();
		return "redirect:/admin/noss"+getParams(pageable, filter);
	}
}
