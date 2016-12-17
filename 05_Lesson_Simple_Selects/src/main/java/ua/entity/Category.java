package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="_name")
	private String name;
	//Item.category
	@OneToMany(mappedBy="category")
	private List<Item> items = new ArrayList<>();
	//@JoinTable дає змогу налаштувати таблицю між зв'язками
	@ManyToMany
	@JoinTable(name="category_name_of_specification_digital",
	//joinColumns дає змогу налаштувати стовпець який є 
	//зовнішнім ключем цієї таблиці
	joinColumns=@JoinColumn(name="id_category"),
	//inverseJoinColumns - налаштовує стовпець протилежної таблиці
	inverseJoinColumns=@JoinColumn(name="id_name_of_specification_digital"))
	private List<NameOfSpecificationDigital> nameOfSpecificationDigitals = new ArrayList<>();
	//@JoinTable дає змогу налаштувати таблицю між зв'язками
	@ManyToMany
	@JoinTable(name="category_name_of_specification_string",
	//joinColumns дає змогу налаштувати стовпець який є
	//зовнішнім ключем цієї таблиці
	joinColumns=@JoinColumn(name="id_category"),
	//inverseJoinColumns - налаштовує стовпець протилежної таблиці
	inverseJoinColumns=@JoinColumn(name="id_name_of_specification_string"))
	private List<NameOfSpecificationString> nameOfSpecificationStrings = new ArrayList<>();
	
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
	public List<NameOfSpecificationDigital> getNameOfSpecificationDigitals() {
		return nameOfSpecificationDigitals;
	}
	public void setNameOfSpecificationDigitals(
			List<NameOfSpecificationDigital> nameOfSpecificationDigitals) {
		this.nameOfSpecificationDigitals = nameOfSpecificationDigitals;
	}
	public List<NameOfSpecificationString> getNameOfSpecificationStrings() {
		return nameOfSpecificationStrings;
	}
	public void setNameOfSpecificationStrings(
			List<NameOfSpecificationString> nameOfSpecificationStrings) {
		this.nameOfSpecificationStrings = nameOfSpecificationStrings;
	}
}
