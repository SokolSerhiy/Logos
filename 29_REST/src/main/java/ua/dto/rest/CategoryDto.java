package ua.dto.rest;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

	private List<NameOfSpecStringDto> nameOfSpecStrings = new ArrayList<>();
	
	private List<NameOfSpecDigitDto> nameOfSpecDigits = new ArrayList<>();

	public List<NameOfSpecStringDto> getNameOfSpecStrings() {
		return nameOfSpecStrings;
	}

	public void setNameOfSpecStrings(List<NameOfSpecStringDto> nameOfSpecStrings) {
		this.nameOfSpecStrings = nameOfSpecStrings;
	}

	public List<NameOfSpecDigitDto> getNameOfSpecDigits() {
		return nameOfSpecDigits;
	}

	public void setNameOfSpecDigits(List<NameOfSpecDigitDto> nameOfSpecDigits) {
		this.nameOfSpecDigits = nameOfSpecDigits;
	}
}