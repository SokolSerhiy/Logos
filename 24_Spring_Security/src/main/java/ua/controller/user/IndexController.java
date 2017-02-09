package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.User;
import ua.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(Principal principal){
//		principal.getName();
		return "user-index";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user-login";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model){
		model.addAttribute("userForm", new User());
		return "user-registration";
	}
	
	@RequestMapping(value="/registration", method=POST)
	public String registration(@ModelAttribute User user){
		userService.save(user);
		return "redirect:/login";
	}
}