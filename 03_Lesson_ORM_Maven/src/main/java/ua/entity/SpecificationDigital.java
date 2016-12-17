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
@Table(name = "name_of_specification_digital")
public class SpecificationDigital {
	// Первинний ключ
	@Id
	// AUTO_INCTEMENT
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "_value")
	private BigDecimal value;
	@ManyToOne(fetch = FetchType.LAZY)
	// Аналогічно @Column але використовується для стовпців які є зовнішніми
	// ключами
	@JoinColumn(name = "id_name_of_specification_digital")
	private NameOfSpecificationDigital nameOfSpecificationDigital;

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
}
