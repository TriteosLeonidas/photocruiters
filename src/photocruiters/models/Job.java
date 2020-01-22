package photocruiters.models;
import java.util.ArrayList;
import java.util.Date;

public class Job {

	private int job_id;
	private int photographer_id;
	private Photographer photographer;
	private PhotoCategory relatedCategory;
	private int employer_id;
	private User employer;
	
	private ArrayList<JobInterested> jobsInterested = new ArrayList<JobInterested>();
	
	private String description;
	private Date job_date;
	private int job_duration;
	private int job_category;
	private float price;
	private String review;
	private int rating;
	private int status;

	/**
	 * Constructor
	 *
	 * @param
	 * @param
	 * @param
	 * @param
	 * @param
	 */
	public Job(int job_id, int employer_id, String description, Date job_date, int job_duration, int job_category, float price, String review, int status) {
		this.job_id = job_id;
		//this.photographer_id = photographer_id;
		this.employer_id = employer_id;
		this.description = description;
		this.job_date = job_date;
		this.job_category = job_category;
		this.job_duration = job_duration;
		this.price = price;
		this.review= review;
		this.status = status;
	}

	/* Getters & Setters */

	/**
	 * @return the job_duration
	 */
	public int getJob_duration() {
		return job_duration;
	}

	/**
	 * @param job_duration the job_duration to set
	 */
	public void setJob_duration(int job_duration) {
		this.job_duration = job_duration;
	}

	/**
	 * @return the photographer_id
	 */
	public int getPhotographer_id() {
		return photographer_id;
	}

	/**
	 * @param photographer_id the photographer_id to set
	 */
	public void setPhotographer_id(int photographer_id) {
		this.photographer_id = photographer_id;
	}

	/**
	 * @return the employer_id
	 */
	public int getEmployer_id() {
		return employer_id;
	}

	/**
	 * @param employer_id the employer_id to set
	 */
	public void setEmployer_id(int employer_id) {
		this.employer_id = employer_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the job_category
	 */
	public int getJob_category() {
		return job_category;
	}

	/**
	 * @param job_category the job_category to set
	 */
	public void setJob_category(int job_category) {
		this.job_category = job_category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getJobs_id() {
		return job_id;
	}

	public void setJobs_id(int jobs_id) {
		this.job_id = job_id;
	}

	public Date getJob_date() {
		return job_date;
	}

	public void setJob_date(Date job_date) {
		this.job_date = job_date;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public Photographer getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}

	public User getEmployer() {
		return employer;
	}

	public void setEmployer(User employer) {
		this.employer = employer;
	}

	public PhotoCategory getRelatedCategory() {
		return relatedCategory;
	}

	public void setRelatedCategory(PhotoCategory relatedCategory) {
		this.relatedCategory = relatedCategory;
	}

	public ArrayList<JobInterested> getJobsInterested() {
		return jobsInterested;
	}

	public void setJobsInterested(ArrayList<JobInterested> jobsInterested) {
		this.jobsInterested = jobsInterested;
	}
	
	

} //End of class
