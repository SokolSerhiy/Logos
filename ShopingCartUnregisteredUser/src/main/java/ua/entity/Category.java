package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category", indexes=@Index(columnList = "_name"))
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private Category parent;
	@OneToMany(mappedBy="parent")
	private List<Category> childs = new ArrayList<>();
	
	private boolean haveChilds;
	
	private int level;
	@Column(name="_name")
	private String name;
	@OneToMany(mappedBy="category")
	private List<Item> items = new ArrayList<>();
	@ManyToMany
	@JoinTable(name="category_name_of_specification_digital",
	joinColumns=@JoinColumn(name="id_category"),
	inverseJoinColumns=@JoinColumn(name="id_name_of_specification_digital"))
	private List<NameOfSpecificationDigital> nameOfSpecificationDigitals = new ArrayList<>();
	@ManyToMany
	@JoinTable(name="category_name_of_specification_string",
	joinColumns=@JoinColumn(name="id_category"),
	inverseJoinColumns=@JoinColumn(name="id_name_of_specification_string"))
	private List<NameOfSpecificationString> nameOfSpecificationStrings = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		if(parent!=null){
			setLevel(parent.level+1);
		}
		this.parent = parent;
	}
	public List<Category> getChilds() {
		return childs;
	}
	public void setChilds(List<Category> childs) {
		this.childs = childs;
	}
	public boolean getHaveChilds() {
		return haveChilds;
	}
	public void setHaveChilds(boolean haveChilds) {
		this.haveChilds = haveChilds;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
