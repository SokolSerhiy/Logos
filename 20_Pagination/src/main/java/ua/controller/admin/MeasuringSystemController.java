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

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;
import ua.validator.MeasuringSystemValidator;

@Controller
@RequestMapping("/admin/ms")
@SessionAttributes(names="ms")
public class MeasuringSystemController {

	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@InitBinder("ms")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MeasuringSystemValidator(measuringSystemService));
	}
	
	@ModelAttribute("ms")
	public MeasuringSystem getForm(){
		return new MeasuringSystem();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("page", measuringSystemService.findAll(pageable));
		return "admin-measuringSystem";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		measuringSystemService.delete(id);
		return "redirect:/admin/ms";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("ms", measuringSystemService.findOne(id));
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		return "admin-measuringSystem";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("ms")@Valid MeasuringSystem form, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()){
			model.addAttribute("measuringSystems", measuringSystemService.findAll());
			return "admin-measuringSystem";
		}
		measuringSystemService.save(form);
		status.setComplete();
		return "redirect:/admin/ms";
	}
}
