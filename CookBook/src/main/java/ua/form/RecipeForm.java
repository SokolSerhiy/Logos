package ua.form;

import java.util.ArrayList;
import java.util.List;

import ua.entity.Amount;
import ua.entity.Country;

public class RecipeForm {
	
	private Long id;
	
	private String title;
	
	private String time;
	
	private List<Amount> amounts = new ArrayList<>();
	
	private Country country;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Amount> getAmounts() {
		return amounts;
	}

	public void setAmounts(List<Amount> amounts) {
		this.amounts = amounts;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}