package photocruiters.models;

import java.util.Date;

public class Job {

	private int job_id;
	private int photographer_id;
	private int employer_id;
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
	public Job(int job_id, int photographer_id, int employer_id, String description, int job_duration, int job_category, float price, String review, int rating, int status) {
		this.job_id = job_id;
		this.photographer_id = photographer_id;
		this.employer_id = employer_id;
		this.description = description;
		this.job_category = job_category;
		this.job_duration = job_duration;
		this.price = price;
		this.review= review;
		this.rating = rating;
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

} //End of class
