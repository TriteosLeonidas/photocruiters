<%@page import="photocruiters.daos.JobDAO"%>
<%@page import="photocruiters.models.User"%>
<%
	String o = request.getParameter("price");
	String j = request.getParameter("jobId");
	float offer = Float.parseFloat(o);
	int jobId = Integer.parseInt(j);
	
	try {
	
		User u = null;
		u = (User)session.getAttribute("loggedInUser");
		int photographerId = u.getUserid();
		
		JobDAO jd = new JobDAO();
		jd.createJobOffer(photographerId, jobId, offer);
		request.setAttribute("message", "You have succesfully provided an offer! Good luck with your offer!");		
	} catch(Exception ex)  {
		request.setAttribute("errorMessage", ex.toString());
	}
		
	RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);
	
%>