package ua.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.editor.CountryEditor;
import ua.entity.Author;
import ua.entity.Country;
import ua.service.AuthorService;
import ua.service.CountryService;

@Controller
@RequestMapping("/admin/author")
@SessionAttributes("author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CountryService countryService;
	
	@InitBinder("author")
	protected void initBinder(WebDataBinder binder){
	   binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
	}
	
	@ModelAttribute("author")
	public Author getForm(){
		return new Author();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("authors", authorService.findAll());
		model.addAttribute("countries", countryService.findAll());
		return "admin-author";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		authorService.delete(id);
		return "redirect:/admin/author";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		show(model);
		model.addAttribute("author", authorService.findOne(id));
		return "admin-author";
	}
	@PostMapping
	public String save(@ModelAttribute("author") Author author, SessionStatus status){
		authorService.save(author);
		status.setComplete();
		return "redirect:/admin/author";
	}
}

