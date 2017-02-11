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
import ua.entity.Producer;
import ua.service.ProducerService;
import ua.validator.ProducerValidator;

@Controller
@RequestMapping("/admin/producer")
@SessionAttributes(names="producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@InitBinder("producer")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProducerValidator(producerService));
	}

	@ModelAttribute("producer")
	public Producer getForm(){
		return new Producer();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", producerService.findAll(filter, pageable));
		status.setComplete();
		return "admin-producer";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		producerService.delete(id);
		return "redirect:/admin/producer"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("producer", producerService.findOne(id));
		model.addAttribute("page", producerService.findAll(filter, pageable));
		return "admin-producer";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("producer")@Valid Producer form, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", producerService.findAll(filter, pageable));
			return "admin-producer";
		}
		producerService.save(form);
		status.setComplete();
		return "redirect:/admin/producer"+getParams(pageable, filter);
	}
}
