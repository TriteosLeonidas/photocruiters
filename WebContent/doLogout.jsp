<%
	session.invalidate();

	request.setAttribute("message", "User logged out!");
	RequestDispatcher dispatcher = application.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);
%>