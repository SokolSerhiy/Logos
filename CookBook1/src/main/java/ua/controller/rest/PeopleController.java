package ua.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.entity.People;
import ua.service.PeopleService;

@RestController
@RequestMapping(value="/people")
public class PeopleController {

	@Autowired
	private PeopleService service;
	
	@GetMapping
	public List<People> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public People findOne(@PathVariable Long id){
		return service.findOne(id);
	}
	
	@PutMapping
	public People save(@RequestBody People people){
		return service.save(people);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus delete(@PathVariable Long id){
		service.delete(id);
		return HttpStatus.OK;
	}
}
