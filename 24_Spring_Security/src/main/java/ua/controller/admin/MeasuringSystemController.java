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
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", measuringSystemService.findAll(filter, pageable));
		return "admin-measuringSystem";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		measuringSystemService.delete(id);
		return "redirect:/admin/ms"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("ms", measuringSystemService.findOne(id));
		show(model, pageable, filter);
		return "admin-measuringSystem";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("ms")@Valid MeasuringSystem form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "admin-measuringSystem";
		}
		measuringSystemService.save(form);
		status.setComplete();
		return "redirect:/admin/ms"+getParams(pageable, filter);
	}
}
