package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Fuel extends AbstractEntity{

	private String name;
	
	@OneToMany(mappedBy="fuel")
	private List<Engine> engines = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Engine> getEngines() {
		return engines;
	}

	public void setEngines(List<Engine> engines) {
		this.engines = engines;
	}
}
