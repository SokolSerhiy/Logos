package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="ingredient", indexes={@Index(columnList = "name")})
public class Ingredient extends AbstractEntity{

	private String name;
	@OneToMany(mappedBy="ingredient")
	private List<Amount> amounts = new ArrayList<>();
	
	private int version;
	@Transient
	private transient MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Amount> getAmounts() {
		return amounts;
	}
	public void setAmounts(List<Amount> amounts) {
		this.amounts = amounts;
	}
}
