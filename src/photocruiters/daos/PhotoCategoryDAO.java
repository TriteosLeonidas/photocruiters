package photocruiters.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import photocruiters.helpers.DB;
import photocruiters.models.PhotoCategory;

public class PhotoCategoryDAO {

	/**
	 * This method returns a List with all Photo Categories
	 * 
	 * @return List<User>
	 */
	public List<PhotoCategory> getPhotoCategories() throws Exception {
		
		List<PhotoCategory> results = new ArrayList<PhotoCategory>();
		DB db = new DB();
		
		try {
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM photo_categories ORDER BY photo_categories_name");
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				PhotoCategory p = new PhotoCategory(rst.getInt("photo_categories_id"), rst.getString("photo_categories_name"));
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
	
	public ArrayList<PhotoCategory> getPhotoCategoriesForPhotographer(int userId) throws Exception {
		
		ArrayList<PhotoCategory> results = new ArrayList<PhotoCategory>();
		DB db = new DB();
		
		try {
			
			String sqlStmt = 	"SELECT * FROM photo_categories "
							+ 	" INNER JOIN users_photocategories ON photo_categories.photo_categories_id = users_photocategories.photo_categories_id "
							+	" WHERE users_photocategories.user_id=?"
							+ 	" ORDER BY photo_categories_name";
			
			
			Connection con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(sqlStmt);
			stmt.setInt(1, userId);
			ResultSet rst = stmt.executeQuery();

			while(rst.next()) {
				PhotoCategory p = new PhotoCategory(rst.getInt("photo_categories_id"), rst.getString("photo_categories_name"));
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
}
