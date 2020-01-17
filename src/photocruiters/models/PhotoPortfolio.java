package photocruiters.models;

public class PhotoPortfolio {

	private int photographerId;
	private User user;
	private byte[] photo;
	private String photoDescription;
	
	public int getPhotographerId() {
		return photographerId;
	}
	public void setPhotographerId(int photographerId) {
		this.photographerId = photographerId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoDescription() {
		return photoDescription;
	}
	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}
	
}
