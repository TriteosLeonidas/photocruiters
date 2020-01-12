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
public class CityDAO {
	
	public City getCity(int cityId) throws Exception {
		City result = null;
		
		DB db = new DB();
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cities WHERE city_id=?");
			stmt.setInt(1, cityId);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				result = new City(rst.getInt("city_id"), rst.getString("city_name"));
			}
			
			rst.close();
			return result;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
	}
	
	/**
	 * This method returns a List with all Cities
	 * 
	 * @return List<User>
	 */
	public List<City> getCities() throws Exception {
		
		List<City> cities = new ArrayList<City>();
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM cities ORDER BY city_name");
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				City c = new City(rst.getInt("city_id"), rst.getString("city_name"));
				cities.add(c);
			}
			
			rst.close();
			return cities;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
	} //End of getUsers

	

} //End of class
