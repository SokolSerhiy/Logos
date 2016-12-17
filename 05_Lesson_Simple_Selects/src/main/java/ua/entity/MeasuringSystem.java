package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "measuring_system")
public class MeasuringSystem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_name")
	private String name;
	//Item.measuringSystems
	@ManyToMany(mappedBy="measuringSystems")
	private List<Item> items = new ArrayList<>();
	//SpecificationDigital.measuringSystems
	@ManyToMany(mappedBy="measuringSystems")
	private List<SpecificationDigital> specificationDigitals = new ArrayList<>();

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
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<SpecificationDigital> getSpecificationDigitals() {
		return specificationDigitals;
	}
	public void setSpecificationDigitals(
			List<SpecificationDigital> specificationDigitals) {
		this.specificationDigitals = specificationDigitals;
	}
}
