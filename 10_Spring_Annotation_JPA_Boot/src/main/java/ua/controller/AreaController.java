package ua.controller;

import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ua.entity.Area;
import ua.service.AreaService;

@Service
public class AreaController {

	private final AreaService service;
	
	private final Scanner sc;

	public AreaController(AreaService service, Scanner sc) {
		this.service = service;
		this.sc = sc;
	}
	
	public void save(){
		String name = readName();
		service.save(new Area(name));
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
		Area area = service.findOne(id);
		area.setName(name);
	}

	private int readId() {
		System.out.println("Enter id");
		return sc.nextInt();
	}

	private String readName() {
		System.out.println("Enter name");
		return sc.next().replaceAll("_", " ");
	}
	
	private void print(Area area){
		System.out.println(area.getId()+" "+area.getName());
	}
}
