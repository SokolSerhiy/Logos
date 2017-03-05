package ua.controller.user;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.User;
import ua.service.ItemService;
import ua.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/")
	public String index(Model model, @CookieValue(defaultValue="0", name="userId") int id, HttpServletResponse response){
		if(id==0){
			id = userService.createNewUser();
			response.addCookie(new Cookie("userId", String.valueOf(id)));
		}
		model.addAttribute("items", itemService.findAll());
		return "user-index";
	}
	
	@GetMapping("/shopping")
	public String shopping(Model model, @CookieValue(defaultValue="0", name="userId") int userId){
		model.addAttribute("items", itemService.findByUserId(userId));
		return "user-shopping";
	}
	
	@GetMapping("/buy/{itemId}")
	public String buy(@CookieValue(defaultValue="0", name="userId") int userId, @PathVariable int itemId){
		userService.addToShoppingCart(userId, itemId);
		return "redirect:/";
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