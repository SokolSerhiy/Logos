package ua.entity;

import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "apartment")
public class Apartment {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String photoUrl;

	private Integer version;

	private BigDecimal price;

	@ManyToOne(fetch = LAZY)
	private RentType rentType;

	private BigDecimal rate;

	private int rooms;

	@OneToMany(mappedBy = "apartment")
	private List<Photo> photos = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	private Area area;

	@ManyToOne(fetch = LAZY)
	private Street street;

	private String number;

	private String description;

	public Apartment(BigDecimal price, RentType rentType, int rooms, Area area, Street street,
			String number, String description) {
		this.price = price;
		this.rentType = rentType;
		this.rooms = rooms;
		this.area = area;
		this.street = street;
		this.number = number;
		this.description = description;
	}
	
	public Apartment() {
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

	public RentType getRentType() {
		return rentType;
	}

	public void setRentType(RentType rentType) {
		this.rentType = rentType;
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

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
