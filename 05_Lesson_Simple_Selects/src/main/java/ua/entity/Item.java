package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_name")
	private String name;
	
	private BigDecimal price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producer")
	private Producer producer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category")
	private Category category;
	//@JoinTable дає змогу налаштувати таблицю між зв'язками
	@ManyToMany
	@JoinTable(name="measuring_system_item",
	//joinColumns дає змогу налаштувати стовпець який є 
	//зовнішнім ключем цієї таблиці
	joinColumns=@JoinColumn(name="id_item"),
	//inverseJoinColumns - налаштовує стовпець протилежної таблиці
	inverseJoinColumns=@JoinColumn(name="id_measuring_system"))
	private List<MeasuringSystem> measuringSystems = new ArrayList<>();
	@ManyToMany
	@JoinTable(name="item_specification_string",
			joinColumns=@JoinColumn(name="id_item"),
			inverseJoinColumns=@JoinColumn(name="id_specification_string"))
	private List<SpecificationString> specificationStrings = new ArrayList<>();
	@ManyToMany
	@JoinTable(name="item_specification_digital",
			joinColumns=@JoinColumn(name="id_item"),
			inverseJoinColumns=@JoinColumn(name="id_specification_digital"))
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<MeasuringSystem> getMeasuringSystems() {
		return measuringSystems;
	}
	public void setMeasuringSystems(List<MeasuringSystem> measuringSystems) {
		this.measuringSystems = measuringSystems;
	}
	public List<SpecificationString> getSpecificationStrings() {
		return specificationStrings;
	}
	public void setSpecificationStrings(
			List<SpecificationString> specificationStrings) {
		this.specificationStrings = specificationStrings;
	}
	public List<SpecificationDigital> getSpecificationDigitals() {
		return specificationDigitals;
	}
	public void setSpecificationDigitals(
			List<SpecificationDigital> specificationDigitals) {
		this.specificationDigitals = specificationDigitals;
	}
}
