package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.entity.Producer;
import ua.service.ProducerService;
import ua.validator.ProducerValidator;

@Controller
@RequestMapping("/admin/producer")
@SessionAttributes(names="producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@ModelAttribute("producer")
	public Producer getForm(){
		return new Producer();
	}
	
	@InitBinder("producer")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProducerValidator(producerService));
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("producers", producerService.findAll());
		return "admin-producer";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		producerService.delete(id);
		return "redirect:/admin/producer";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("producer", producerService.findOne(id));
		model.addAttribute("producers", producerService.findAll());
		return "admin-producer";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("producer")@Valid Producer form, BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()){
			model.addAttribute("producers", producerService.findAll());
			return "admin-producer";
		}
		producerService.save(form);
		status.setComplete();
		return "redirect:/admin/producer";
	}
}
