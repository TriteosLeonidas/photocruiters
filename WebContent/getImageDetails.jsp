<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@page import="photocruiters.helpers.DB"%>
<%
	Blob image = null;
	DB db = new DB();
	byte[] imgData = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection con = db.getConnection();
	try {

		stmt = con.prepareStatement("SELECT photo FROM photographers_portfolio WHERE photographer_id = ?");
		stmt.setString(1, request.getParameter("photo_id"));
		rs = stmt.executeQuery();
				
		if (rs.next()) {
			image = rs.getBlob(1);
			imgData = image.getBytes(1, (int) image.length());
		} else {
			out.println("Display Blob Example");
			out.println("image not found for given id");
			return;
		}
		// display the image
		response.setContentType("image/jpg");
		OutputStream o = response.getOutputStream();
		o.write(imgData);
		o.flush();
		o.close();
	} catch (Exception e) {
		out.println("Unable To Display image");
		out.println("Image Display Error=" + e.getMessage());
		return;
	} finally {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
%>