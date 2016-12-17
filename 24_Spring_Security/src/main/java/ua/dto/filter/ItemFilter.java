package ua.dto.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFilter {
	
	private static final Pattern PEATTERN = Pattern.compile("[0-9]+");

	private String search = "";
	
	private String maxPrice = "";
	
	private String minPrice = "";
	
	private Integer max;
	
	private Integer min;
	
	private List<Integer> producerIds = new ArrayList<>();
	
	private List<Integer> ssIds = new ArrayList<>();
	
	private List<SpecDigitFilter> specDigitFilters = new ArrayList<>();
	
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(PEATTERN.matcher(maxPrice).matches())max = Integer.valueOf(maxPrice);
		this.maxPrice = maxPrice;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		if(PEATTERN.matcher(minPrice).matches())min = Integer.valueOf(minPrice);
		this.minPrice = minPrice;
	}

	public List<Integer> getSsIds() {
		return ssIds;
	}

	public void setSsIds(List<Integer> ssIds) {
		this.ssIds = ssIds;
	}

	public List<Integer> getProducerIds() {
		return producerIds;
	}

	public void setProducerIds(List<Integer> producerIds) {
		this.producerIds = producerIds;
	}

	public List<SpecDigitFilter> getSpecDigitFilters() {
		return specDigitFilters;
	}

	public void setSpecDigitFilters(List<SpecDigitFilter> specDiditFilters) {
		this.specDigitFilters = specDiditFilters;
	}
}