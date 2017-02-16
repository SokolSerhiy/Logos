package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.entity.Producer;
import ua.service.ProducerService;

@Controller
@RequestMapping("/admin/producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("producers", producerService.findAll());
		return "admin-producer";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		producerService.delete(id);
		return "redirect:/admin/producer";
	}
	
	@PostMapping
	public String save(@RequestParam String name){
		Producer producer = new Producer();
		producer.setName(name);
		producerService.save(name);
		return "redirect:/admin/producer";
	}
}
