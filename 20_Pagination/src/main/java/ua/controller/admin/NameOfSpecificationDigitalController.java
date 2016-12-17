package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import ua.entity.NameOfSpecificationDigital;
import ua.service.NameOfSpecificationDigitalService;
import ua.validator.NameOfSpecificationDigitalValidator;

@Controller
@RequestMapping("/admin/nosd")
@SessionAttributes(names="nosd")
public class NameOfSpecificationDigitalController {

	@Autowired
	private NameOfSpecificationDigitalService digitalService;
	
	@ModelAttribute("nosd")
	public NameOfSpecificationDigital getForm(){
		return new NameOfSpecificationDigital();
	}
	
	@InitBinder("nosd")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameOfSpecificationDigitalValidator(digitalService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("page", digitalService.findAll(pageable));
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		digitalService.delete(id);
		return "redirect:/admin/nosd";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("nosd",digitalService.findOne(id));
		model.addAttribute("nosds", digitalService.findAll());
		return "admin-nameOfSpecificationDigital";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("nosd")@Valid NameOfSpecificationDigital form, BindingResult br, SessionStatus status, Model model){
		if(br.hasErrors()){
			model.addAttribute("nosds", digitalService.findAll());
			return "admin-nameOfSpecificationDigital";
		}
		digitalService.save(form);
		status.setComplete();
		return "redirect:/admin/nosd";
	}
}
