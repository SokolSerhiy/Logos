package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="specification_string")
public class SpecificationString {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="_name")
	private String name;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_name_of_specification_string")
	private NameOfSpecificationString nameOfSpecificationString;
	
	@ManyToMany(mappedBy="specificationDigitals")
	private List<Item> items = new ArrayList<>();
	
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
	public NameOfSpecificationString getNameOfSpecificationString() {
		return nameOfSpecificationString;
	}
	public void setNameOfSpecificationString(
			NameOfSpecificationString nameOfSpecificationString) {
		this.nameOfSpecificationString = nameOfSpecificationString;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}