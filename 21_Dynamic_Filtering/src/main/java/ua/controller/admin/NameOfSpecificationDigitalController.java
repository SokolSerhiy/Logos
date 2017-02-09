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
import ua.entity.NameOfSpecificationDigital;
import ua.service.NameOfSpecificationDigitalService;
import ua.validator.NameOfSpecificationDigitalValidator;

@Controller
@RequestMapping("/admin/nosd")
@SessionAttributes(names="nosd")
public class NameOfSpecificationDigitalController {

	@Autowired
	private NameOfSpecificationDigitalService digitalService;
	
	@InitBinder("nosd")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameOfSpecificationDigitalValidator(digitalService));
	}
	
	@ModelAttribute("nosd")
	public NameOfSpecificationDigital getForm(){
		return new NameOfSpecificationDigital();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", digitalService.findAll(filter, pageable));
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		digitalService.delete(id);
		return "redirect:/admin/nosd"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("nosd",digitalService.findOne(id));
		model.addAttribute("page", digitalService.findAll(filter, pageable));
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("nosd")@Valid NameOfSpecificationDigital form, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", digitalService.findAll(filter, pageable));
			return "admin-nameOfSpecificationDigital";
		}
		digitalService.save(form);
		status.setComplete();
		return "redirect:/admin/nosd"+getParams(pageable, filter);
	}
}
