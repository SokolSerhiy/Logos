package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.ItemService;

@Controller
@RequestMapping("/admin/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("items", itemService.findAll());
		return "admin-item";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		itemService.delete(id);
		return "redirect:/admin/item";
	}
}
