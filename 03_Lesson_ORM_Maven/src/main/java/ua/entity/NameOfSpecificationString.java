package ua.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Означає що це сутність БД
@Entity
//для того що б таблиці мали назву як і в БД
@Table(name="name_of_specification_string")
public class NameOfSpecificationString {
	//Первинний ключ
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того що б стовпці мали назву як і в БД
	@Column(name="_name")
	private String name;
	
	private List<SpecificationString> specificationStrings;
	
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
}
