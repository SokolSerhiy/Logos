package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Producer;
import ua.service.ProducerService;

@Controller
@RequestMapping("/admin/producer")
@SessionAttributes(names="form")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@ModelAttribute("form")
	public Producer getForm(){
		return new Producer();
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
		model.addAttribute("form", producerService.findOne(id));
		model.addAttribute("producers", producerService.findAll());
		return "admin-producer";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("form") Producer form, SessionStatus status){
		producerService.save(form);
		status.setComplete();
		return "redirect:/admin/producer";
	}
}
