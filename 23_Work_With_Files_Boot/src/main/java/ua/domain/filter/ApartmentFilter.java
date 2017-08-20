package ua.domain.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ApartmentFilter {
	
	private static final Pattern decimalPattern = Pattern.compile("^([0-9]{1,17}\\.[0-9]{0,2})|([0-9]{1,17}\\,[0-9]{0,2})|([0-9]{1,17})$");

	private String min = "";
	
	private String max = "";
	
	private String street = "";
	
	private String minRate = "";
	
	private String rentType = "";
	
	private List<String> areas = new ArrayList<>();
	
	public boolean isValid() {
		return !min.isEmpty()||!max.isEmpty()||!street.isEmpty()||!minRate.isEmpty()||!rentType.isEmpty()||!areas.isEmpty();
	}
	
	public String getMinRate() {
		return minRate;
	}

	public void setMinRate(String minRate) {
		if(decimalPattern.matcher(minRate).matches()) {
			minRate.replace(',', '.');
			this.minRate = minRate;
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<String> getAreas() {
		return areas;
	}

	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(decimalPattern.matcher(min).matches()) {
			minRate.replace(',', '.');
			this.min = min;
		}
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(decimalPattern.matcher(max).matches()) {
			minRate.replace(',', '.');
			this.max = max;
		}
	}
}
