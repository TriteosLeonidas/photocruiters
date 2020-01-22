package photocruiters.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import photocruiters.helpers.DB;
import photocruiters.models.City;
import photocruiters.models.Job;
import photocruiters.models.JobInterested;
import photocruiters.models.PhotoCategory;
import photocruiters.models.Photographer;
import photocruiters.models.User;

import java.sql.*;

/**
 * JobDAO provides all the necessary methods related to job's. 
 * In further lessons we will change all the code of the body of the methods and use
 * JDBC API in order to connect the the database and store/retrieve users etc.
 * 
 * @author 
 *
 */
public class JobDAO {
		
	public List<Job> searchJobs(String citiesFilter, String categoriesFilter, String durationsFilter, String jobDate) throws Exception {
		
		citiesFilter=citiesFilter.trim().replace(" ", ",");
		
		categoriesFilter=categoriesFilter.trim().replace(" ", ",");
		
		durationsFilter=durationsFilter.trim().replace(" ", ",");
		
		List<Job> results = new ArrayList<Job>();
		
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			
			String sqlStmt ="SELECT * FROM jobs INNER JOIN users ON users.user_id = jobs.employer_id ";
			String whereStmt = " WHERE jobs.status = 0 ";
			
			// Join tables based on criteria
			if(categoriesFilter!="") {
				// sqlStmt = sqlStmt + " INNER JOIN users_photocategories ON users.user_id = users_photocategories.user_id ";
				whereStmt = whereStmt + " AND jobs.job_category IN (" + categoriesFilter + ") ";
			}
			
			if(citiesFilter!="") {
				whereStmt = whereStmt + " AND users.city_id IN (" + citiesFilter + ")";
			}
			
			if(durationsFilter!="")
			{
				whereStmt = whereStmt + " AND job.job_duration IN (" + durationsFilter + ")";
			}
			
			
			PreparedStatement stmt = con.prepareStatement(sqlStmt + whereStmt);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				
				Job j = new Job(
						rst.getInt("job_id"),
						rst.getInt("employer_id"),
						rst.getString("description"), 
						rst.getDate("job_date"), 
						rst.getInt("job_duration"), 
						rst.getInt("job_category"), 
						rst.getFloat("price"),
						rst.getString("review"),
						rst.getInt("status")
					);
				
					User u = null;
					if(rst.getInt("employer_id") > 0) {
						UserDAO ud = new UserDAO();
						u = ud.getUser(rst.getInt("employer_id"));
					}
			
					Photographer p = null;
					if(rst.getInt("photographer_id") > 0) {
						PhotographersDAO phd = new PhotographersDAO();
						p = phd.getPhotographer(rst.getInt("photographer_id"));
					}
					
					PhotoCategory pc = null;
					if(rst.getInt("job_category") > 0) {
						PhotoCategoryDAO pcd = new PhotoCategoryDAO();
						pc = pcd.getPhotoCategory(rst.getInt("job_category"));
					}
						
					j.setPhotographer(p);
					j.setEmployer(u);
					j.setRelatedCategory(pc);
					
					results.add(j);
			}
			
			rst.close();
			return results;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
	}
	
	public List<Job> getJobsForEmployer(int userId) throws Exception {
		List<Job> jobs = new ArrayList<Job>();
		DB db = new DB();
		
		
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM jobs WHERE employer_id=?");
			stmt.setInt(1, userId);
			ResultSet rst = stmt.executeQuery();
			
			while(rst.next()) {
				
				ArrayList<JobInterested> offers = new ArrayList<JobInterested>();
				
				Job j = new Job(
							rst.getInt("job_id"),
							rst.getInt("employer_id"),
							rst.getString("description"), 
							rst.getDate("job_date"), 
							rst.getInt("job_duration"), 
							rst.getInt("job_category"), 
							rst.getFloat("price"),
							rst.getString("review"),
							rst.getInt("status")
						);
				
				PhotographersDAO phd = new PhotographersDAO();
				
				PreparedStatement stmtOffers = con.prepareStatement("SELECT * FROM job_interested WHERE job_id = ?");
				stmtOffers.setInt(1, rst.getInt("job_id"));
				ResultSet rstOffers = stmtOffers.executeQuery();
				while(rstOffers.next()) {
					Photographer pi = phd.getPhotographer(rstOffers.getInt("photographer_id"));
					JobInterested ji = new JobInterested(rstOffers.getInt("job_interested_id"),j,pi,rstOffers.getFloat("price"));
					offers.add(ji);
				}
				
				Photographer p = null;
				if(rst.getInt("photographer_id") > 0) {
					
					p = phd.getPhotographer(rst.getInt("photographer_id"));
				}
				
				PhotoCategory pc = null;
				if(rst.getInt("job_category") > 0) {
					PhotoCategoryDAO pcd = new PhotoCategoryDAO();
					pc = pcd.getPhotoCategory(rst.getInt("job_category"));
				}
					
				j.setPhotographer(p);

				j.setRelatedCategory(pc);
				
				j.setJobsInterested(offers);
				
				jobs.add(j);
			}
			
			rst.close();
			return jobs;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
	}
	
	
	/**
	 * This method returns a List with all Jobs
	 * 
	 * @return List<Job>
	 */
	public List<Job> getJobs() throws Exception {
		
		List<Job> jobs = new ArrayList<Job>();
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM jobs");
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				Job j = new Job(
						rst.getInt("job_id"),
						rst.getInt("employer_id"),
						rst.getString("description"), 
						rst.getDate("job_date"), 
						rst.getInt("job_duration"), 
						rst.getInt("job_category"), 
						rst.getFloat("price"),
						rst.getString("review"),
						rst.getInt("status")
					);
				jobs.add(j);
			}
			
			rst.close();
			return jobs;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
	} //End of getJobs

	/**
	 * Create/create new Job.
	 * 
	 * @param job, Job
	 * @throws Exception, if encounter any error.
	 */
	public void create(Job job) throws Exception {
				
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO jobs (employer_id, description, job_date, job_duration, job_category, price,status) VALUES (?,?,?,?,?,?,?) ");
			stmt.setInt(1, job.getEmployer_id());
			stmt.setString(2, job.getDescription());
			
			java.sql.Date d = new java.sql.Date(job.getJob_date().getYear(), job.getJob_date().getMonth(), job.getJob_date().getDay());
			stmt.setDate(3, d);
			stmt.setInt(4, job.getJob_duration());
			stmt.setInt(5, job.getJob_category());
			stmt.setFloat(6, job.getPrice());
			stmt.setInt(7, job.getStatus());
			stmt.executeUpdate();
						
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
		
	}//end of register

	public void createJobOffer(int photographerId, int jobId, float offer) throws Exception {
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO job_interested (job_id, photographer_id, price) VALUES (?,?,?) ");
			stmt.setInt(1, jobId);
			stmt.setInt(2, photographerId);
			stmt.setFloat(3, offer);
			
			stmt.executeUpdate();
						
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
	}
	
	public void awardOffer(int photographerId, int jobId) throws Exception {
		
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();

			// UPDATE jobs with the winner and status = 1
			PreparedStatement stmt = con.prepareStatement("UPDATE jobs SET photographer_id = ?, status=1 WHERE job_id=?");
			stmt.setInt(1, photographerId);
			stmt.setInt(2, jobId);
			stmt.executeUpdate();
			
			// DELETE all offers from table job_interested
			PreparedStatement stmt2 = con.prepareStatement("DELETE FROM job_interested WHERE job_id=?");
			stmt2.setInt(1, jobId);
			stmt2.executeUpdate();
						
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
		
	}
} //End of class