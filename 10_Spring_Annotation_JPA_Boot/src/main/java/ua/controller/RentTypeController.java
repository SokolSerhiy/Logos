package ua.controller;

import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.RentType;
import ua.service.RentTypeService;

@Service
public class RentTypeController {

	private final RentTypeService service;
	
	private final Scanner sc;

	@Autowired
	public RentTypeController(RentTypeService service, Scanner sc) {
		this.service = service;
		this.sc = sc;
	}
	
	public void save(){
		String name = readName();
		service.save(new RentType(name));
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
		RentType rentType = service.findOne(id);
		rentType.setName(name);
	}

	private int readId() {
		System.out.println("Enter id");
		return sc.nextInt();
	}

	private String readName() {
		System.out.println("Enter name");
		return sc.next().replaceAll("_", " ");
	}
	
	private void print(RentType rentType){
		System.out.println(rentType.getId()+" "+rentType.getName());
	}
	
}
