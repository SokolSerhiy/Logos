package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.MeasuringSystemService;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.NameOfSpecificationStringService;
import ua.service.ProducerService;

@Controller
@RequestMapping("/admin/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MeasuringSystemService measuringSystemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@Autowired
	private NameOfSpecificationDigitalService nameOfSpecificationDigitalService;
	
	@Autowired
	private ProducerService producerService;
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("items", itemService.findAll());
		return "admin-item";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		itemService.delete(id);
		return "redirect:/admin/item";
	}
	
	@RequestMapping("/add/{id}")
	public String showAdd(@PathVariable int id, Model model){
		model.addAttribute("measuringSystems",measuringSystemService.findAll());
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("category", categoryService.findOne(id));
		model.addAttribute("nosss", nameOfSpecificationStringService.findByCategoryId(id));
		model.addAttribute("nosds", nameOfSpecificationDigitalService.findByCategoryId(id));
		model.addAttribute("producers", producerService.findAll());
		return "admin-item";
	}
	
	@RequestMapping(method=POST)
	//id категорії з схованого інпуту
	public String save(@RequestParam int categoryId,
			//назва з текстового інпуту
			@RequestParam String name,
			//id виробника з селекту
			@RequestParam int producerId,
			//id-шки SpecificationString 
			//з усіх селектів
			//їх може бути стільки скільки 
			//назв специфікацій стрічкових
			//зв'язано з категорією
			@RequestParam List<Integer> ssIds,
			//id-шки NameOfSpecificationDigital
			//з схованих інпутів
			//їх може бути стільки скільки назв 
			//специфікацій цифрових
			//зв'язано з категорією
			@RequestParam List<Integer> nosdIds,
			//список значень з інпутів, на основі яких 
			//буде збудовано об'єкти
			//SpecificationDigital
			@RequestParam List<BigDecimal> sdValues,
			//id-шки одиниць виміру, які вибираються 
			//з селекту для кожного окремого
			//SpecificationDigital
			@RequestParam List<Integer> mss,
			//ціна, звичайний інпут
			@RequestParam BigDecimal price,
			//id одиниць виміру в яких 
			//продається товар
			@RequestParam int msId
			//всі списки подані в одній послідовності
			//для самих сміливих в сервісі метод
			//який все це зберігає
			){
		itemService.save(categoryId, name, producerId, ssIds, nosdIds, sdValues, mss, price, msId);
		return "redirect:/admin/item";
	}
}
