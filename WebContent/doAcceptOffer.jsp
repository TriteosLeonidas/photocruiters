<%@page import="photocruiters.daos.JobDAO"%>
<%

	String w = request.getParameter("winner");
	String j = request.getParameter("jobId");
	int winner = Integer.parseInt(w);
	int jobId = Integer.parseInt(j);
	
	try {
		
		JobDAO jd = new JobDAO();
		jd.awardOffer(winner, jobId);
		request.setAttribute("message", "You have succesfully awarded the offer!");		
	} catch(Exception ex)  {
		request.setAttribute("errorMessage", ex.toString());
	}
		
	RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);

%>