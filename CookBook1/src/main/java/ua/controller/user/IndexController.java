package ua.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.User;
import ua.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return "user-index";
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	public String save(@ModelAttribute("user")User user){
		userService.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "user-login";
	}
	
	@RequestMapping(value="/human")
	public String people(){
		return "user-people";
	}
	
}
