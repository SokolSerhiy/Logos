package ua.dto.rest;

import java.util.List;

public class NameOfSpecStringDto {

	private String name;
	
	private List<SpecStringDto> specStrings;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SpecStringDto> getSpecStrings() {
		return specStrings;
	}

	public void setSpecStrings(List<SpecStringDto> specStrings) {
		this.specStrings = specStrings;
	}
}