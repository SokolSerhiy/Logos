package ua.controller.admin;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.service.utils.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.service.CategoryService;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.NameOfSpecificationStringService;
import ua.validator.CategoryValidator;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names="category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private NameOfSpecificationStringService nameOfSpecificationStringService;
	
	@Autowired
	private NameOfSpecificationDigitalService nameOfSpecificationDigitalService;
	
	@InitBinder("category")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new CategoryValidator(categoryService));
	}
	
	@ModelAttribute("category")
	public Category getForm(){
		return new Category();
	}
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("page", categoryService.findAll(filter, pageable));
		return "admin-category";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.delete(id);
		return "redirect:/admin/category"+getParams(pageable, filter);
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		model.addAttribute("category",categoryService.findOne(id));
		model.addAttribute("page", categoryService.findAll(filter, pageable));
		return "admin-category";
	}
	
	@RequestMapping(method=POST)
	public String save(@ModelAttribute("category") @Valid Category category,BindingResult br, SessionStatus status,Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		if(br.hasErrors()){
			model.addAttribute("page", categoryService.findAll(filter, pageable));
			return "admin-category";
		}
		categoryService.save(category);
		status.setComplete();
		return "redirect:/admin/category"+getParams(pageable, filter);
	}
	
	@RequestMapping("/add/noss/{id}")
	public String showAddNoss(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		Category category = categoryService.loadedNoss(id);
		model.addAttribute("category", category);
		model.addAttribute("page", nameOfSpecificationStringService.findAllExcludeLoaded(filter, pageable, category));
		return "admin-addNoss";
	}
	
	@RequestMapping("/add/noss/{id}/{nossId}")
	public String saveAddNoss(@PathVariable int id, @PathVariable int nossId, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.addNoss(id, nossId);
		return "redirect:/admin/category/add/noss/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/delete/noss/{id}/{nossId}")
	public String deleteNoss(@PathVariable int id,@PathVariable int nossId,  @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.deleteNoss(id, nossId);
		return "redirect:/admin/category/add/noss/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/add/nosd/{id}")
	public String showAddNosd(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		Category category = categoryService.loadedNosd(id);
		model.addAttribute("category", category);
		model.addAttribute("page", nameOfSpecificationDigitalService.findAllExcludeLoaded(filter, pageable, category));
		return "admin-addNosd";
	}
	
	@RequestMapping("/add/nosd/{id}/{nosdId}")
	public String saveAddNosd(@PathVariable int id,@PathVariable int nosdId, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.addNosd(id, nosdId);
		return "redirect:/admin/category/add/nosd/"+id+getParams(pageable, filter);
	}
	
	@RequestMapping("/delete/nosd/{id}/{nosdId}")
	public String deleteNosd(@PathVariable int id,@PathVariable int nosdId,  @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		categoryService.deleteNosd(id, nosdId);
		return "redirect:/admin/category/add/nosd/"+id+getParams(pageable, filter);
	}
}