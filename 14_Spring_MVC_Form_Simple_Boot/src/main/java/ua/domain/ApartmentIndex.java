package ua.domain;

import java.math.BigDecimal;

public class ApartmentIndex {

	private Integer id;
	
	private String photoUrl;
	
	private Integer version;
	
	private BigDecimal price;
	
	private String rentType;
	
	private String area;
	
	private BigDecimal rate;
	
	private int rooms;
	
	public ApartmentIndex(Integer id, String photoUrl, Integer version, BigDecimal price, String rentType, String area,
			BigDecimal rate, int rooms) {
		this.id = id;
		this.photoUrl = photoUrl;
		this.version = version;
		this.price = price;
		this.rentType = rentType;
		this.area = area;
		this.rate = rate;
		this.rooms = rooms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "ApartmentIndex [id=" + id + ", photoUrl=" + photoUrl + ", version=" + version + ", price=" + price
				+ ", rentType=" + rentType + ", area=" + area + ", rate=" + rate + ", rooms=" + rooms + "]";
	}
}