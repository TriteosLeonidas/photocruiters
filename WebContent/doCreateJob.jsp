<%@page import="photocruiters.models.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="photocruiters.models.Job"%>
<%@page import="photocruiters.daos.JobDAO"%>
<%
	String description = request.getParameter("description");
	String d = request.getParameter("date");
	Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
	int duration =  Integer.parseInt(request.getParameter("duration"));
	int photocat = Integer.parseInt(request.getParameter("photocat"));
	float price = Float.parseFloat(request.getParameter("price"));
	
	try {
	
		User u = null;
		u = (User)session.getAttribute("loggedInUser");
		int employerId = u.getUserid();
		
		Job j = new Job(0,employerId,description,date,duration,photocat,price,"",0);
		
		JobDAO jd = new JobDAO();
		jd.create(j);
		request.setAttribute("message", "New job created!");		
	} catch(Exception ex)  {
		request.setAttribute("errorMessage", ex.toString());
	}
		
	RequestDispatcher dispatcher = application.getRequestDispatcher("/myjobs.jsp");
	dispatcher.forward(request, response);
	
%>