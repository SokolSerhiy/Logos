package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "name_of_specification_digital")
public class NameOfSpecificationDigital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_name")
	private String name;
	//SpecificationDigital.nameOfSpecificationDigital
	@OneToMany(mappedBy="nameOfSpecificationDigital")
	private List<SpecificationDigital> specificationDigitals = new ArrayList<>();
	//Category.nameOfSpecificationDigitals
	@ManyToMany(mappedBy="nameOfSpecificationDigitals")
	private List<Category> categories = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SpecificationDigital> getSpecificationDigitals() {
		return specificationDigitals;
	}
	public void setSpecificationDigitals(
			List<SpecificationDigital> specificationDigitals) {
		this.specificationDigitals = specificationDigitals;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
