package ua.controller;

import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ua.entity.Street;
import ua.service.StreetService;

@Service
public class StreetController {

	private final StreetService service;
	
	private final Scanner sc;
	
	public StreetController(StreetService service, Scanner sc) {
		this.service = service;
		this.sc = sc;
	}
	
	public void save(){
		String name = readName();
		service.save(new Street(name));
	}
	
	public void findAll(){
		service.findAll().forEach(this::print);
	}
	
	public void findOne() {
		int id = readId();
		print(service.findOne(id));
	}
	
	public void delete() {
		findAll();
		int id = readId();
		service.delete(id);
	}
	
	@Transactional
	public void update(){
		findAll();
		int id = readId();
		String name = readName();
		Street street = service.findOne(id);
		street.setName(name);
	}

	private int readId() {
		System.out.println("Enter id");
		return sc.nextInt();
	}

	private String readName() {
		System.out.println("Enter name");
		return sc.next().replaceAll("_", " ");
	}
	
	private void print(Street street){
		System.out.println(street.getId()+" "+street.getName());
	}
}
