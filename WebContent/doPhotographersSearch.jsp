<%@page import="photocruiters.models.Photographer"%>
<%@page import="java.util.List"%>
<%@page import="photocruiters.daos.PhotographersDAO"%>
<%
	String citiesFilter = request.getParameter("city_checkboxes");
	String categoriesFilter = request.getParameter("photocat_checkboxes");
	String ratingsFilter = request.getParameter("rating_checkboxes");

	PhotographersDAO phd = new PhotographersDAO();
	List<Photographer> results = phd.searchPhotographers(citiesFilter, categoriesFilter, ratingsFilter);

	request.setAttribute("results", results);
	RequestDispatcher dispatcher = application.getRequestDispatcher("/phsearch.jsp");
	dispatcher.forward(request, response);
%>



