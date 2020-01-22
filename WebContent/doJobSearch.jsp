<%@page import="photocruiters.models.Job"%>
<%@page import="java.util.List"%>
<%@page import="photocruiters.daos.JobDAO"%>
<%
	String selectedCities = request.getParameter("city_checkboxes");
	String selectedCategories = request.getParameter("photocat_checkboxes");
	String selectedDurations = request.getParameter("duration_checkboxes");
	String jobDate = request.getParameter("job_date");
	
	JobDAO jd = new JobDAO();
	List<Job> results = jd.searchJobs(selectedCities, selectedCategories, selectedDurations, jobDate);

	request.setAttribute("results", results);
	RequestDispatcher dispatcher = application.getRequestDispatcher("/jobssearch.jsp");
	dispatcher.forward(request, response);

%>