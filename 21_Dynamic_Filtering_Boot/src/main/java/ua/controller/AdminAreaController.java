package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.domain.filter.SimpleFilter;
import ua.entity.Area;
import ua.service.AreaService;

@Controller
@RequestMapping("/admin/area")
@SessionAttributes("area")
public class AdminAreaController {
	
	private final AreaService service;
	
	@Autowired
	public AdminAreaController(AreaService service) {
		this.service = service;
	}
	
	@ModelAttribute("area")
	public Area getForm(){
		return new Area();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("areas", service.findAll(pageable, filter));
		return "area";
	}
	
	@PostMapping
	public String add(@ModelAttribute("area") @Valid Area area, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()){
			show(model, pageable, filter);
			return "area";
		}
		service.save(area);
		status.setComplete();
		return "redirect:/admin/area"+buildParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("area", service.findOne(id));
		show(model, pageable, filter);
		return "area";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		service.delete(id);
		return "redirect:/admin/area"+buildParams(pageable, filter);
	}
	
	private String buildParams(Pageable pageable, SimpleFilter filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("?page=");
		sb.append(pageable.getPageNumber());
		sb.append("&size=");
		sb.append(pageable.getPageSize());
		if(!filter.getSearch().isEmpty()) {
			sb.append("&search=");
			sb.append(filter.getSearch());
		}
		if(pageable.getSort()!=null){
			sb.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				sb.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC) sb.append(",desc");
			});
		}
		return sb.toString();
	}
	
}
