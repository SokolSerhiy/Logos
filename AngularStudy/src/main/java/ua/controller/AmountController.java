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

import ua.dto.AmountDto;
import ua.entity.Amount;
import ua.filter.AmountFilter;
import ua.service.AmountService;

@RestController
@RequestMapping("/amount")
public class AmountController {
	
	@Autowired
	private AmountService service;
	
	@ModelAttribute("filter")
	public AmountFilter getFilter(){
		return new AmountFilter();
	}
	
	@GetMapping(headers={"Accept=application/json"}, params={"page","size"})
	public Page<AmountDto> get(@PageableDefault Pageable pageable, @ModelAttribute("filter") AmountFilter filter){
		return service.findAll(pageable, filter);
	}
	
	@GetMapping(headers={"Accept=application/json"})
	public List<AmountDto> get(){
		return service.findAll();
	}
	
	@PostMapping(headers={"Accept=application/json"})
	public HttpStatus post(@RequestBody Amount amount){
		service.save(amount);
		return HttpStatus.OK;
	}
	
	@DeleteMapping(value="/{id}", headers={"Accept=application/json"})
	public HttpStatus delete(@PathVariable Long id){
		service.delete(id);
		return HttpStatus.OK;
	}
	
	@PutMapping(value="/{id}", headers={"Accept=application/json"})
	public HttpStatus put(@PathVariable Long id,@RequestBody Amount amount){
//		amountDto.setId(id);
		service.save(amount);
		return HttpStatus.OK;
	}

}
