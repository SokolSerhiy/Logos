package ua.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ua.validation.annotation.UniqueRentType;

@Entity
@Table(name="rent_type")
public class RentType {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Integer id;
	@UniqueRentType(message="Такий тип оренди вже існує")
	private String name;
	
	@OneToMany(mappedBy="rentType")
	private List<Apartment> apartments = new ArrayList<>();
	
	public RentType(String name) {
		this.name = name;
	}
	
	public RentType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public RentType() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}
}