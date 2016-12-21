package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import ua.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("categories", categoryService.findAll());
		return "admin-category";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		categoryService.delete(id);
		return "redirect:/admin/category";
	}
	//вказуємо що це метод POST (статичний імпорт)
	@RequestMapping(method=POST)
	//вказуємо що в параметрах буде параметр name
	public String save(@RequestParam String name){
		//передаємо в сервіс для збереження
		//об'єкту Category
		categoryService.save(name);
		//після пост методів в більшості випадків 
		//потрібно робити радірект, щоб запобігти
		//повторній відправці форми
		//після редіректу всі параметри будуть видалені
		return "redirect:/admin/category";
	}
}