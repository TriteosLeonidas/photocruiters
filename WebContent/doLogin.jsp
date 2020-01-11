<%@page import="photocruiters.models.User"%>
<%@page import="photocruiters.daos.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%
	try {
		ArrayList<String> errors = new ArrayList();
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username==null || username.isEmpty()) {
			errors.add("You must provide a username");
		}
		
		if(password==null || password.isEmpty()) {
			errors.add("You must provide a password");
		}
		
		
		if(errors.size()==0) {
			// Register the user on the DB
			UserDAO ud = new UserDAO();
			User u = ud.authenticate(username, password);
			
			session.setAttribute("loggedInUser", u);
			
			//request.setAttribute("message", "User registered successfully");
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