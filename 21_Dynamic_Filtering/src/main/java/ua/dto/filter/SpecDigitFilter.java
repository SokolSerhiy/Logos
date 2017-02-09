package ua.dto.filter;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class SpecDigitFilter {
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2}|([0-9]{1,18}))$");

	private Integer nameId;
	
	private String min = "";
	
	private String max = "";
	
	private BigDecimal maxValue;
	
	private BigDecimal minValue;
	
	private Integer msId;
	
	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public Integer getNameId() {
		return nameId;
	}

	public void setNameId(Integer nameId) {
		this.nameId = nameId;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(PATTERN.matcher(min).matches())minValue = new BigDecimal(min.replace(',', '.'));
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(PATTERN.matcher(max).matches())maxValue = new BigDecimal(max.replace(',', '.'));
		this.max = max;
	}

	public Integer getMsId() {
		return msId;
	}

	public void setMsId(Integer msId) {
		this.msId = msId;
	}
}