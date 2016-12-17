package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.service.CategoryService;

@Controller
//якщо над класом стоїть @RequestMapping 
//з посиланням це означає що всі такі анотації
//продовжують це посилання якщо мають такий атрибут
//якщо ж не мають то працюють за цим посиланням
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	//тут немає посилання тому цей метод спрацює
	//за посиланням /admin/category та методом GET
	@RequestMapping
	//все що нам потрібно можна прописати у вхідних параметрах
	//і спрінг передасть це в метод
	public String show(Model model){
		//Model інтерфейс за допомогою якого
		//передаємо дані на представлення
		//перший параметр методу вказує яке буде
		//імя в списку категорій на представлені
		model.addAttribute("categories", categoryService.findAll());
		return "admin-category";
	}
	//а цей метод спрацює за посиланням 
	// /admin/category/delete/{якась цифра}
	@RequestMapping("/delete/{id}")
	//@PathVariable - анотація яка витягує 
	//певну змінну частину з посилання
	//використовується тоді коли посилання без цієї
	//частини не працює
	//в цьому прикладі додаток не реагує на посилання
	// /admin/category/delete
	//також можна відразу вказати необхідний тип даних
	//в результаті виконання цього методу
	//обов'язково потрібно перенаправити користувача
	//на інше посилання
	//тому що якщо користувач залишиться за цим посиланням
	//і оновить сторінку то викличе повторне спрацьовування
	//цього методу, а такого id в базі вже немає(
	public String delete(@PathVariable int id){
		categoryService.delete(id);
		return "redirect:/admin/category";
	}
}