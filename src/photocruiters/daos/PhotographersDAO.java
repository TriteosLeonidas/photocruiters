package photocruiters.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Blob;

import photocruiters.helpers.DB;
import photocruiters.models.City;
import photocruiters.models.PhotoCategory;
import photocruiters.models.Photographer;
import photocruiters.models.User;

public class PhotographersDAO {

	public List<Photographer> searchPhotographers(String citiesFilter, String categoriesFilter, String ratingsFilter) throws Exception {
		
		citiesFilter=citiesFilter.trim().replace(" ", ",");
		
		categoriesFilter=categoriesFilter.trim().replace(" ", ",");
		
		ratingsFilter=ratingsFilter.trim().replace(" ", ",");
		
		List<Photographer> results = new ArrayList<Photographer>();
		
		// TODO: Implement SQL seach with filters...
		
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			
			String sqlStmt ="SELECT * FROM users ";
			String whereStmt = " WHERE role = 1 ";
			
			// Join tables based on criteria
			if(categoriesFilter!="") {
				sqlStmt = sqlStmt + " INNER JOIN users_photocategories ON users.user_id = users_photocategories.user_id ";
				whereStmt = whereStmt + " AND photo_categories_id IN (" + categoriesFilter + ") ";
			}
			
			if(citiesFilter!="") {
				whereStmt = whereStmt + " AND city_id IN (" + citiesFilter + ")";
			}
			
			if(ratingsFilter!="")
			{
				sqlStmt = sqlStmt.replace("SELECT * FROM users ", "SELECT *, AVG(rating) as AvgRating FROM users ");
				sqlStmt = sqlStmt + " INNER JOIN jobs ON jobs.photographer_id = users.user_id ";
				sqlStmt = sqlStmt + " GROUP BY photographer_id ";
				whereStmt = whereStmt.replace(" WHERE", " HAVING ");
				String ratings[] = ratingsFilter.split(",");
				boolean caseSelected = false;
				for(String r : ratings) {
					switch(r) {
						case "1": whereStmt = whereStmt + (caseSelected ? " OR " : " AND ") + "(AvgRating BETWEEN 0 AND 1) "; caseSelected=true; break;
						case "2": whereStmt = whereStmt + (caseSelected ? " OR " : " AND ") + "(AvgRating BETWEEN 1.01 AND 2) "; caseSelected=true; break;
						case "3": whereStmt = whereStmt + (caseSelected ? " OR " : " AND ") + "(AvgRating BETWEEN 2.01 AND 3) "; caseSelected=true; break;
						case "4": whereStmt = whereStmt + (caseSelected ? " OR " : " AND ") + "(AvgRating BETWEEN 3.01 AND 4) "; caseSelected=true; break;
						case "5": whereStmt = whereStmt + (caseSelected ? " OR " : " AND ") + "(AvgRating BETWEEN 4.01 AND 5) "; caseSelected=true; break;
					}
				}
			}
			
			
			PreparedStatement stmt = con.prepareStatement(sqlStmt + whereStmt);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				
				CityDAO cd = new CityDAO();
				City c = cd.getCity(rst.getInt("city_id"));
				
				PhotoCategoryDAO pc = new PhotoCategoryDAO();
				ArrayList<PhotoCategory> relatedPhotoCategories = pc.getPhotoCategoriesForPhotographer(rst.getInt("user_id"));
				
				Photographer p = new Photographer(	rst.getInt("user_id"),
						rst.getString("first_name"), 
						rst.getString("last_name"), 
						rst.getString("email"), 
						rst.getInt("role"), 
						rst.getString("password"),
						rst.getString("mobile"),
						rst.getString("cv"),
						rst.getString("address"),
						c);
				try {
					p.setPhotographerRating(rst.getDouble("AvgRating"));
				} catch(Exception ex) {
					// do nothing - we don't have AvgRating on our results
				}
				
				
				p.setRelatedPhotoCategories(relatedPhotoCategories);
				
				results.add(p);
			}
			
			rst.close();
			return results;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
		
	}

	public Photographer getPhotographer(int userId) throws Exception {
		
		Photographer result = null;
		
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			
			String sqlStmt ="SELECT * FROM users "; 
			String whereStmt = " WHERE role = 1 AND user_id=?";
			
			PreparedStatement stmt = con.prepareStatement(sqlStmt + whereStmt);
			stmt.setInt(1, userId);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				
				CityDAO cd = new CityDAO();
				City c = cd.getCity(rst.getInt("city_id"));
				
				PhotoCategoryDAO pc = new PhotoCategoryDAO();
				ArrayList<PhotoCategory> relatedPhotoCategories = pc.getPhotoCategoriesForPhotographer(rst.getInt("user_id"));
				
				result = new Photographer(	rst.getInt("user_id"),
						rst.getString("first_name"), 
						rst.getString("last_name"), 
						rst.getString("email"), 
						rst.getInt("role"), 
						rst.getString("password"),
						rst.getString("mobile"),
						rst.getString("cv"),
						rst.getString("address"),
						c);
				
				// TODO: result.setPhotographerRating()
				
				
				result.setRelatedPhotoCategories(relatedPhotoCategories);
				
			}
			
			rst.close();
			return result;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
		
		 
	}
	
	public List<Integer> getPhotoIdsForPhotographer(int userId) throws Exception {
		List<Integer> results = new ArrayList<Integer>();
		
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			
			String sqlStmt ="SELECT photographer_id FROM photographers_portfolio WHERE user_id=? "; 
			
			PreparedStatement stmt = con.prepareStatement(sqlStmt);
			stmt.setInt(1, userId);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				results.add(rst.getInt("photographer_id"));
			}
			
			rst.close();
			return results;
			
		} catch(Exception ex) {
			throw ex;
		} finally {
			db.close();
			
		}
	}

	public byte[] getImage(int imageId) throws Exception {
		Blob image = null;
		DB db = new DB();
		byte[] imgData = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = db.getConnection();
		try {

			stmt = con.prepareStatement("SELECT photo FROM photographers_portfolio WHERE photographer_id = ?");
			stmt.setInt(1, imageId);
			rs = stmt.executeQuery();
					
			if (rs.next()) {
				image = rs.getBlob(1);
				imgData = image.getBytes(1, (int) image.length());
			} else {
				throw new  Exception("image not found for given id");
			}
			// display the image
			return imgData;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
