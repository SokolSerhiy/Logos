package ua.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Car extends AbstractEntity{
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Vendor vendor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Body body;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Engine engine;
	
	private int year;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Transmission transmission;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Color color;
	
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
