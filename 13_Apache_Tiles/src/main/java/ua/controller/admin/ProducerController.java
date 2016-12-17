package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.ProducerService;

@Controller
@RequestMapping("/admin/producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
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
}
