package lv.lgs.jdbc.domain;

public class Author {
	
	private int id;
	private String firsName;
	private String lastName;
	
	public Author(){
	}
	
	public Author(String firsName, String lastName) {
		super();
		this.firsName = firsName;
		this.lastName = lastName;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
