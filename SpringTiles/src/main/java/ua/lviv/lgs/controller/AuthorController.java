package ua.lviv.lgs.controller;

import java.util.List;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService service;
	
	@RequestMapping(value = "/showAllAuthors")
	public String getAllAuthors(Model model) {
		List<Author> authors = service.getAllAuthors();
		model.addAttribute("allAuthors", authors);
		return "author-all";
	}
	
	@RequestMapping(value = "/createAuthor") 
	public String createAuthorPage() {
		return "author-new";
	}
	
	@RequestMapping(value = "/showAllAuthors", method = RequestMethod.POST)
	public String createAuthor(@RequestParam(value = "name") String name,
			@RequestParam(value = "age") String age) {
		int ageInt = Integer.parseInt(age);
		service.insertAuthor(name, ageInt);
		return "redirect:/showAllAuthors";
	}
}
