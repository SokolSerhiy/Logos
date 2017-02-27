package ua.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.entity.Country;
import ua.filter.BasicFilter;
import ua.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountyController {

	@Autowired
	private CountryService service;
	
	@ModelAttribute("filter")
	public BasicFilter getFilter(){
		return new BasicFilter();
	}
	
	@GetMapping(headers={"Accept=application/json"}, params={"page","size"})
	public Page<Country> get(@PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
		return service.findAll(pageable, filter);
	}
	
	@GetMapping(headers={"Accept=application/json"})
	public List<Country> get(){
		return service.findAll();
	}
	
	@PostMapping(headers={"Accept=application/json"})
	public HttpStatus post(@RequestBody Country country){
		service.save(country);
		return HttpStatus.OK;
	}
	
	@DeleteMapping(value="/{id}", headers={"Accept=application/json"})
	public HttpStatus delete(@PathVariable Long id){
		service.delete(id);
		return HttpStatus.OK;
	}
	
	@PutMapping(value="/{id}", headers={"Accept=application/json"})
	public HttpStatus put(@PathVariable Long id,@RequestBody Country country){
		country.setId(id);
		service.save(country);
		return HttpStatus.OK;
	}
}
