package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import ua.service.MeasuringSystemService;

@Controller
@RequestMapping("/admin/ms")
public class MeasuringSystemController {

	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("measuringSystems", measuringSystemService.findAll());
		return "admin-measuringSystem";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		measuringSystemService.delete(id);
		return "redirect:/admin/ms";
	}
	
	@RequestMapping(method=POST)
	public String save(@RequestParam String name){
		measuringSystemService.save(name);
		return "redirect:/admin/ms";
	}
}
