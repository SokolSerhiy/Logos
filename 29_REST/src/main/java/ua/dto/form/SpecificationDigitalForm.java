package ua.dto.form;

import java.util.ArrayList;
import java.util.List;

import ua.entity.MeasuringSystem;
import ua.entity.NameOfSpecificationDigital;


public class SpecificationDigitalForm {

	private int id;
	
	private String value;
	
	private NameOfSpecificationDigital nameOfSpecificationDigital;
	
	private List<MeasuringSystem> measuringSystems = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public NameOfSpecificationDigital getNameOfSpecificationDigital() {
		return nameOfSpecificationDigital;
	}

	public void setNameOfSpecificationDigital(
			NameOfSpecificationDigital nameOfSpecificationDigital) {
		this.nameOfSpecificationDigital = nameOfSpecificationDigital;
	}

	public List<MeasuringSystem> getMeasuringSystems() {
		return measuringSystems;
	}

	public void setMeasuringSystems(List<MeasuringSystem> measuringSystems) {
		this.measuringSystems = measuringSystems;
	}
	
}
