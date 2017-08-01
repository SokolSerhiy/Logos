package ua.domain.view;

public class PhotoDto {

	private String photoUrl;
	
	private Integer version;

	public PhotoDto(String photoUrl, Integer version) {
		this.photoUrl = photoUrl;
		this.version = version;
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
}
