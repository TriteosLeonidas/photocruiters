<%@page import="photocruiters.models.User"%>
<%@page import="photocruiters.daos.UserDAO"%>
<%@page import="photocruiters.models.City"%>
<%@page import="java.util.ArrayList"%>
<%
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
		
		City city = new City(cityId, "");
		
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
			User u = new User(0, name, surname, email, role, password, mobile, cv, address, city);
			ud.register(u);
			
			
			request.setAttribute("message", "User registered successfully");
			RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			
		} else {
			// This method forwards the request to the specified page, including message and model!
			String message = "<ol>";
			for(String error : errors) {
				message = message = "<li>" + error + "</li>";
			}
			message = message + "</ol>";
			
			request.setAttribute("errorMessage", message);
			
			RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	} catch (Exception ex) {
		request.setAttribute("errorMessage", ex);
		RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
		
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


%>