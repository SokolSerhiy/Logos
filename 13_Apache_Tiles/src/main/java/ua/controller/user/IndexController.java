package ua.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.CategoryService;
import ua.service.ItemService;

@Controller
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("categories", categoryService.findAll());
		return "user-index";
	}
	
	@RequestMapping("/category/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("items", itemService.findByCategoryId(id));
		return "user-category";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
}
