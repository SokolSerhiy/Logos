package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
// для того що б таблиці мали назву як і в БД
@Table(name = "measuring_system")
public class MeasuringSystem {
	// Первинний ключ
	@Id
	// AUTO_INCTEMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// для того що б стовпці мали назву як і в БД
	@Column(name = "_name")
	private String name;

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
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
