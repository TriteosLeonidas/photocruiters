package photocruiters.models;

public class JobInterested {

	private int id;
	private Job job;
	private Photographer photographer;
	private float price;
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Photographer getPhotographer() {
		return photographer;
	}
	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public JobInterested(int id, Job job, Photographer photographer, float price) {
		//super();
		this.id = id;
		this.job = job;
		this.photographer = photographer;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}

