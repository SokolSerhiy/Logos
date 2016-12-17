package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.editor.CategoryEditor;
import ua.editor.MeasuringSystemEditor;
import ua.editor.NameOfSpecificationDigitalEditor;
import ua.editor.ProducerEditor;
import ua.editor.SpecificationStringEditor;
import ua.entity.Category;
import ua.entity.Item;
import ua.entity.MeasuringSystem;
import ua.entity.NameOfSpecificationDigital;
import ua.entity.Producer;
import ua.entity.SpecificationString;
import ua.service.CategoryService;
import ua.service.ItemService;
import ua.service.MeasuringSystemService;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.NameOfSpecificationStringService;
import ua.service.ProducerService;
import ua.service.SpecificationStringService;

@Controller
@RequestMapping("/admin/item")
@SessionAttributes(names="item")
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
	private SpecificationStringService specificationStringService;
	
	@Autowired
	private ProducerService producerService;
	
	@InitBinder("item")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.registerCustomEditor(Producer.class, new ProducerEditor(producerService));
		binder.registerCustomEditor(SpecificationString.class, new SpecificationStringEditor(specificationStringService));
		binder.registerCustomEditor(NameOfSpecificationDigital.class, new NameOfSpecificationDigitalEditor(nameOfSpecificationDigitalService));
		binder.registerCustomEditor(MeasuringSystem.class, new MeasuringSystemEditor(measuringSystemService));
	}
	
	@ModelAttribute("item")
	public Item getForm(){
		return new Item();
	}
	
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
	public String save(@ModelAttribute("item") Item item, SessionStatus sessionStatus){
		itemService.save(item);
		sessionStatus.setComplete();
		return "redirect:/admin/item";
	}
}
