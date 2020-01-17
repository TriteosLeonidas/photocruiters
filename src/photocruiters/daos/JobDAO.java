package photocruiters.daos;

import java.util.ArrayList;
import java.util.List;

import photocruiters.helpers.DB;
import photocruiters.models.City;
import photocruiters.models.Job;
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
				Job j = new Job(rst.getString("description"), rst.getInt("job_date"), rst.getInt("job_duration"), rst.getInt("job_category"), rst.getFloat("price"));
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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO jobs (employer_id, description, job_date, job_duration, job_category, price) VALUES (?,?,?,?,?,?) ");
			stmt.setInt(1, user.getEmployer_id());
			stmt.setString(2, user.getDescription());
			stmt.setInt(3, user.getJob_date());
			stmt.setInt(4, user.getJob_duration());
			stmt.setInt(5, user.getJob_category());
			stmt.setFloat(6, user.getPrice());
			stmt.executeUpdate();
						
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
		
	}//end of register

} //End of class