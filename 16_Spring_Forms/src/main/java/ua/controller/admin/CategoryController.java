package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.entity.Category;
import ua.entity.NameOfSpecificationDigital;
import ua.entity.NameOfSpecificationString;
import ua.service.CategoryService;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.NameOfSpecificationStringService;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names="form")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@Autowired
	private NameOfSpecificationDigitalService nameOfSpecificationDigitalService;
	
	@ModelAttribute("form")
	public Category getForm(){
		return new Category();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("categories", categoryService.findAll());
		return "admin-category";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		categoryService.delete(id);
		return "redirect:/admin/category";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("form",categoryService.findOne(id));
		model.addAttribute("categories", categoryService.findAll());
		return "admin-category";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("form") Category category, SessionStatus status){
		categoryService.save(category);
		status.setComplete();
		return "redirect:/admin/category";
	}
	
	@RequestMapping("/add/noss/{id}")
	public String showAddNoss(@PathVariable int id, Model model){
		Category category = categoryService.loadedNoss(id);
		model.addAttribute("category", category);
		List<NameOfSpecificationString> nosss = nameOfSpecificationStringService.findAll();
		nosss.removeAll(category.getNameOfSpecificationStrings());
		model.addAttribute("nosss", nosss);
		return "admin-addNoss";
	}
	
	@RequestMapping("/add/noss/{id}/{nossId}")
	public String saveAddNoss(@PathVariable int id,@PathVariable int nossId){
		categoryService.addNoss(id, nossId);
		return "redirect:/admin/category/add/noss/"+id;
	}
	
	@RequestMapping("/delete/noss/{id}/{nossId}")
	public String deleteNoss(@PathVariable int id,@PathVariable int nossId){
		categoryService.deleteNoss(id, nossId);
		return "redirect:/admin/category/add/noss/"+id;
	}
	
	@RequestMapping("/add/nosd/{id}")
	public String showAddNosd(@PathVariable int id, Model model){
		Category category = categoryService.loadedNosd(id);
		model.addAttribute("category", category);
		List<NameOfSpecificationDigital> nosds = nameOfSpecificationDigitalService.findAll();
		nosds.removeAll(category.getNameOfSpecificationDigitals());
		model.addAttribute("nosds", nosds);
		return "admin-addNosd";
	}
	
	@RequestMapping("/add/nosd/{id}/{nosdId}")
	public String saveAddNosd(@PathVariable int id,@PathVariable int nosdId){
		categoryService.addNosd(id, nosdId);
		return "redirect:/admin/category/add/nosd/"+id;
	}
	
	@RequestMapping("/delete/nosd/{id}/{nosdId}")
	public String deleteNosd(@PathVariable int id,@PathVariable int nosdId){
		categoryService.deleteNosd(id, nosdId);
		return "redirect:/admin/category/add/nosd/"+id;
	}
}