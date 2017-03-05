package ua.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ua.dto.rest.CategoryDto;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.NameOfSpecificationStringService;

@RestController
public class CategoryControllerRest {

	@Autowired
	private NameOfSpecificationStringService stringService;
	
	@Autowired
	private NameOfSpecificationDigitalService digitalService;
	
	@GetMapping(value="/category/{id}")
	public CategoryDto getCategory(@PathVariable int id){
		CategoryDto dto = new CategoryDto();
		dto.setNameOfSpecDigits(digitalService.findByCategoryIdDto(id));
		dto.setNameOfSpecStrings(stringService.findByCategoryIdDto(id));
		return dto;
	}
}