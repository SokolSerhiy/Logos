package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="measuring_system")
public class MeasuringSystem extends AbstractEntity{

	private String name;
	
	@OneToMany(mappedBy="system")
	private List<Amount> amounts = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(List<Amount> amounts) {
		this.amounts = amounts;
	}
}
