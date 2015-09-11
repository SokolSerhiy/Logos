package ua.lviv.lgs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.service.AuthorService;

//This annotation tells Spring context to create Controller Bean
//Ця анотація вказує Spring context створити Контролер Bean, який призначений для обробки URL запитів.
@Controller
public class AuthorController {
	@Autowired
	private AuthorService service;
	
	//This annotation mapping web requests onto handler method bellow. 
	// Ця анотація мапує (створює bean) веб запиту на метод нижче, який обробляє цей запит. 2 головні параметри: URL i Http метод.
	@RequestMapping(value = "/showAllAuthors")
	public String getAllAuthors(Model model) {
		List<Author> authors = service.getAllAuthors();
		model.addAttribute("allAuthors", authors);
		
		//Controller`s methods return String value, which represents a name of web page.
		// Методи контролера повертають стрічку, яка являє собою ім'я веб сторінки, яка повинна відкритись. Саме до цієї стрічки дописується префікс і суфікс.
		return "authors";
	}
	
	@RequestMapping(value = "/createAuthor") 
	public String createAuthorPage() {
		return "newAuthor";
	}
	
	//@RequestParam gets input value from web page using input name.
	//@RequestPAram отримує вхідне (введене) значення з веб сторінки, використовуючи ім'я input тегу.
	@RequestMapping(value = "/showAllAuthors", method = RequestMethod.POST)
	public String createAuthor(@RequestParam(value = "name") String name,
			@RequestParam(value = "age") String age) {
		int ageInt = Integer.parseInt(age);
		service.insertAuthor(name, ageInt);
		return "redirect:/showAllAuthors";
	}
}
