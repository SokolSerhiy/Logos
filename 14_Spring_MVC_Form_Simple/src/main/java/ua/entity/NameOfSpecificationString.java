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
@Table(name="name_of_specification_string")
public class NameOfSpecificationString {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="_name")
	private String name;
	//SpecificationString.nameOfSpecificationString
	@OneToMany(mappedBy="nameOfSpecificationString")
	private List<SpecificationString> specificationStrings;
	//Category.nameOfSpecificationStrings
	@ManyToMany(mappedBy="nameOfSpecificationStrings")
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
	public List<SpecificationString> getSpecificationStrings() {
		return specificationStrings;
	}
	public void setSpecificationStrings(List<SpecificationString> specificationStrings) {
		this.specificationStrings = specificationStrings;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
