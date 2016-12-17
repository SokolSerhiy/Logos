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
@Table(name = "specification_digital")
public class SpecificationDigital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_value")
	private BigDecimal value;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_name_of_specification_digital")
	private NameOfSpecificationDigital nameOfSpecificationDigital;
	//@JoinTable дає змогу налаштувати таблицю між зв'язками
	@ManyToMany
	@JoinTable(name="measuring_system_specification_digital",
	//joinColumns дає змогу налаштувати стовпець який є 
	//зовнішнім ключем цієї таблиці
	joinColumns=@JoinColumn(name="id_specification_digital"),
	//inverseJoinColumns - налаштовує стовпець протилежної таблиці
	inverseJoinColumns=@JoinColumn(name="id_measuring_system"))
	private List<MeasuringSystem> measuringSystems = new ArrayList<>();
	@ManyToMany(mappedBy="specificationDigitals")
	private List<Item> items = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public NameOfSpecificationDigital getNameOfSpecificationDigital() {
		return nameOfSpecificationDigital;
	}
	public void setNameOfSpecificationDigital(
			NameOfSpecificationDigital nameOfSpecificationDigital) {
		this.nameOfSpecificationDigital = nameOfSpecificationDigital;
	}
	public List<MeasuringSystem> getMeasuringSystems() {
		return measuringSystems;
	}
	public void setMeasuringSystems(List<MeasuringSystem> measuringSystems) {
		this.measuringSystems = measuringSystems;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
