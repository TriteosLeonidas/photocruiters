package photocruiters.models;

import java.util.ArrayList;

public class Photographer extends User {

	private ArrayList<PhotoCategory> relatedPhotoCategories = new ArrayList<PhotoCategory>();
	private double photographerRating;
	
	public double getPhotographerRating() {
		return photographerRating;
	}

	public void setPhotographerRating(double photographerRating) {
		this.photographerRating = photographerRating;
	}

	public Photographer(int userid, String name, String surname, String email, int role, String password, String mobile,
			String cv, String address, City city) {
		super(userid, name, surname, email, role, password, mobile, cv, address, city);
		
	}

	public ArrayList<PhotoCategory> getRelatedPhotoCategories() {
		return relatedPhotoCategories;
	}

	public void setRelatedPhotoCategories(ArrayList<PhotoCategory> relatedPhotoCategories) {
		this.relatedPhotoCategories = relatedPhotoCategories;
	}
	
	

}
