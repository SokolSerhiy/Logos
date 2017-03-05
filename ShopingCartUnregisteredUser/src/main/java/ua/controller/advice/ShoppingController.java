package ua.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.dto.Quantity;
import ua.service.ItemService;

@ControllerAdvice
public class ShoppingController {
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("quantity")
	public Quantity getQuantity(@CookieValue(defaultValue="0", name="userId") int userId){
		int count = itemService.findCount(userId);
		return new Quantity(count);
	}
}
