package ua.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Apartment;
import ua.entity.Area;
import ua.entity.RentType;
import ua.entity.Street;
import ua.service.ApartmentService;
import ua.service.AreaService;
import ua.service.RentTypeService;
import ua.service.StreetService;

@Service
public class ApartmentController {

	private final ApartmentService service;
	
	private final RentTypeService rentTypeService;
	
	private final AreaService areaService;
	
	private final StreetService streetService;
	
	private final Scanner sc;

	@Autowired
	public ApartmentController(ApartmentService service, RentTypeService rentTypeService, AreaService areaService,
			StreetService streetService, Scanner sc) {
		this.service = service;
		this.rentTypeService = rentTypeService;
		this.areaService = areaService;
		this.streetService = streetService;
		this.sc = sc;
	}

	public void save(){
		BigDecimal price = readPrice();
		System.out.println("Choose rent type");
		rentTypeService.findAll().forEach(this::printRentType);
		int rentTypeId = readId();
		RentType rentType = rentTypeService.findOne(rentTypeId);
		int rooms = readRooms();
		System.out.println("Choose area");
		areaService.findAll().forEach(this::printArea);
		int areaId = readId();
		Area area = areaService.findOne(areaId);
		System.out.println("Choose street");
		streetService.findAll().forEach(this::printStreet);
		int streetId = readId();
		Street street = streetService.findOne(streetId);
		String number = readNumber();
		String description = readDescription();
		service.save(new Apartment(price, rentType, rooms, area, street, number, description));
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
		BigDecimal price = readPrice();
		System.out.println("Choose rent type");
		rentTypeService.findAll().forEach(this::printRentType);
		int rentTypeId = readId();
		RentType rentType = rentTypeService.findOne(rentTypeId);
		int rooms = readRooms();
		System.out.println("Choose area");
		areaService.findAll().forEach(this::printArea);
		int areaId = readId();
		Area area = areaService.findOne(areaId);
		System.out.println("Choose street");
		streetService.findAll().forEach(this::printStreet);
		int streetId = readId();
		Street street = streetService.findOne(streetId);
		String number = readNumber();
		String description = readDescription();
		Apartment apartment = service.findOne(id);
		apartment.setArea(area);
		apartment.setDescription(description);
		apartment.setNumber(number);
		apartment.setPrice(price);
		apartment.setRentType(rentType);
		apartment.setRooms(rooms);
		apartment.setStreet(street);
	}

	private int readId() {
		System.out.println("Enter id");
		return sc.nextInt();
	}

	private void print(Apartment apartment){
		System.out.println(apartment.getId()+" "+
				apartment.getArea().getName()+" "+
				apartment.getStreet().getName()+" "+
				apartment.getNumber()+" "+
				apartment.getRooms()+" "+
				apartment.getDescription()+" "+
				apartment.getPrice()+" "+
				apartment.getRentType().getName());
	}
	
	private void printStreet(Street street){
		System.out.println(street.getId()+" "+street.getName());
	}
	
	private void printArea(Area area){
		System.out.println(area.getId()+" "+area.getName());
	}
	
	private void printRentType(RentType type){
		System.out.println(type.getId()+" "+type.getName());
	}
	
	private String readDescription() {
		System.out.println("Enter description");
		return sc.next();
	}
	
	private String readNumber() {
		System.out.println("Enter number of building");
		return sc.next();
	}
	
	private int readRooms() {
		System.out.println("Enter count of rooms");
		return sc.nextInt();
	}
	
	private BigDecimal readPrice() {
		System.out.println("Enter price");
		return new BigDecimal(sc.next().replaceAll(",", "."));
	}
}
