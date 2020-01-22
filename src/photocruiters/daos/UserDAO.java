package photocruiters.daos;

import java.util.ArrayList;
import java.util.List;

import photocruiters.helpers.DB;
import photocruiters.models.City;
import photocruiters.models.User;
import java.sql.*;


/**
 * UserDAO provides all the necessary methods related to user's. 
 * In further lessons we will change all the code of the body of the methods and use
 * JDBC API in order to connect the the database and store/retrieve users etc.
 * 
 * @author 
 *
 */
public class UserDAO {
		


	public User getUser(int id) throws Exception {
		User u = null;
		DB db = new DB();
		
		try {
			
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE user_id=?");
			stmt.setInt(1, id);
			
			ResultSet rst = stmt.executeQuery();
			
			if(rst.next()) {
								
				CityDAO cd = new CityDAO();
				City c = cd.getCity(rst.getInt("city_id"));
				
				u = new User(	rst.getInt("user_id"),
								rst.getString("first_name"), 
								rst.getString("last_name"), 
								rst.getString("email"), 
								rst.getInt("role"), 
								rst.getString("password"),
								rst.getString("mobile"),
								rst.getString("cv"),
								rst.getString("address"),
								c);
			} else {
				throw new Exception("Wrong username or password");
			}
			
			rst.close();
			return u;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
	}
	
	/**
	 * This method is used to authenticate a user.
	 * 
	 * @param username, String
	 * @param password, String
	 * @return User, the User object
	 * @throws Exception, if the credentials are not valid
	 */
	public User authenticate(String email, String password) throws Exception {
		
		User u = null;
		DB db = new DB();
		
		try {
			
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rst = stmt.executeQuery();
			
			if(rst.next()) {
								
				CityDAO cd = new CityDAO();
				City c = cd.getCity(rst.getInt("city_id"));
				
				u = new User(	rst.getInt("user_id"),
								rst.getString("first_name"), 
								rst.getString("last_name"), 
								rst.getString("email"), 
								rst.getInt("role"), 
								rst.getString("password"),
								rst.getString("mobile"),
								rst.getString("cv"),
								rst.getString("address"),
								c);
			} else {
				throw new Exception("Wrong username or password");
			}
			
			rst.close();
			return u;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
		
	} //End of authenticate
	
	/**
	 * Register/create new User.
	 * 
	 * @param user, User
	 * @throws Exception, if encounter any error.
	 */
	public void register(User user) throws Exception {
				
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO users (role, first_name, last_name, email, password, mobile, cv, city_id, address) VALUES (?,?,?,?,?,?,?,?,?) ");
			stmt.setInt(1, user.getRole());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getSurname());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getMobile());
			stmt.setString(7, user.getCv());
			stmt.setInt(8, user.getCity().getId());
			stmt.setString(9, user.getAddress());
			stmt.executeUpdate();
						
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}	
		
	}//end of register

} //End of class
