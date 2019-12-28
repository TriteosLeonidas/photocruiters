package photocruiters.servlets;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photocruiters.daos.UserDAO;
import photocruiters.models.City;
import photocruiters.models.User;

public class RegisterServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<String> errors = new ArrayList();
	
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String cv = request.getParameter("cv");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			String confirm = request.getParameter("confirm");
			int role = Integer.parseInt(request.getParameter("role"));
			int cityId = Integer.parseInt(request.getParameter("city"));
			
			City city = new City();
			city.setId(cityId);
			
			if(name.length()<3) {
				errors.add("Name must be at least 3 characters long");
			}
			
			if(surname.length()<3) {
				errors.add("Surname must be at least 3 characters long");
			}
			
			if(address.length()<5) {
				errors.add("Address must be at least 5 characters long");
			}
			
			if(password.length()<5) {
				errors.add("Password must be at least 5 characters long");
			}
			
			if(!password.equals(confirm)) 
			{
				errors.add("Password and confirm do not match");
			}
			
			if(errors.size()==0) {
				// Register the user on the DB
				UserDAO ud = new UserDAO();
				User u = new User(name, surname, email, role, password, mobile, cv, address, city);
				ud.register(u);
			}
		} catch (Exception ex) {
			
		}
		
	}
}
