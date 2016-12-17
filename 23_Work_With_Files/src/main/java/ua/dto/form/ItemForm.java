package ua.dto.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Category;
import ua.entity.MeasuringSystem;
import ua.entity.Producer;
import ua.entity.SpecificationString;


public class ItemForm {

	private int id;
	
	private String name;
	
	private String price;
	
	private Producer producer;
	
	private Category category;
	
	private List<MeasuringSystem> measuringSystems = new ArrayList<>();
	
	private List<SpecificationString> specificationStrings = new ArrayList<>();
	
	private List<SpecificationDigitalForm> specificationDigitals = new ArrayList<>();
	
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<MeasuringSystem> getMeasuringSystems() {
		return measuringSystems;
	}

	public void setMeasuringSystems(List<MeasuringSystem> measuringSystems) {
		this.measuringSystems = measuringSystems;
	}

	public List<SpecificationString> getSpecificationStrings() {
		return specificationStrings;
	}

	public void setSpecificationStrings(
			List<SpecificationString> specificationStrings) {
		this.specificationStrings = specificationStrings;
	}

	public List<SpecificationDigitalForm> getSpecificationDigitals() {
		return specificationDigitals;
	}

	public void setSpecificationDigitals(
			List<SpecificationDigitalForm> specificationDigitals) {
		this.specificationDigitals = specificationDigitals;
	}
}
