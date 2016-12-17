package ua.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
// для того що б таблиці мали назву як і в БД
@Table(name = "item")
public class Item {
	// Первинний ключ
	@Id
	// AUTO_INCTEMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// для того що б стовпці мали назву як і в БД
	@Column(name = "_name")
	private String name;
	
	private BigDecimal price;
	@ManyToOne(fetch = FetchType.LAZY)
	// Аналогічно @Column але використовується для стовпців які є зовнішніми
	// ключами
	@JoinColumn(name = "id_producer")
	private Producer producer;
	@ManyToOne(fetch = FetchType.LAZY)
	// Аналогічно @Column але використовується для стовпців які є зовнішніми
	// ключами
	@JoinColumn(name = "id_category")
	private Category category;
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
}
