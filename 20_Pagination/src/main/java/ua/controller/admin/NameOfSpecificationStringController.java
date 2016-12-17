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

import ua.entity.NameOfSpecificationString;
import ua.service.NameOfSpecificationStringService;
import ua.validator.NameOfSpecificationStringValidator;

@Controller
@RequestMapping("/admin/noss")
@SessionAttributes(names="noss")
public class NameOfSpecificationStringController {

	@Autowired
	private NameOfSpecificationStringService stringService;
	
	@ModelAttribute("noss")
	public NameOfSpecificationString getForm(){
		return new NameOfSpecificationString();
	}
	
	@InitBinder("noss")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new NameOfSpecificationStringValidator(stringService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("page", stringService.findAll(pageable));
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("noss", stringService.findOne(id));
		model.addAttribute("nosss", stringService.findAll());
		return "admin-nameOfSpecificationString";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		stringService.delete(id);
		return "redirect:/admin/noss";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("noss")@Valid NameOfSpecificationString form,BindingResult br, SessionStatus status, Model model){
		if(br.hasErrors()){
			model.addAttribute("nosss", stringService.findAll());
			return "admin-nameOfSpecificationString";
		}
		stringService.save(form);
		status.setComplete();
		return "redirect:/admin/noss";
	}
}
