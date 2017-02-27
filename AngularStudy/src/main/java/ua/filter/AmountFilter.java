package ua.filter;

import java.util.ArrayList;
import java.util.List;

public class AmountFilter {

	private String search = "";
	
	private List<Long> ingredientIds = new ArrayList<>();

	private List<Long> msIds = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Long> getIngredientIds() {
		return ingredientIds;
	}

	public void setIngredientIds(List<Long> ingredientIds) {
		this.ingredientIds = ingredientIds;
	}

	public List<Long> getMsIds() {
		return msIds;
	}

	public void setMsIds(List<Long> msIds) {
		this.msIds = msIds;
	}
	
	
}
