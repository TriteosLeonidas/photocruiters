<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="photocruiters.daos.*" %>
<%@page import="photocruiters.models.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String errorMessage=null;
	if(request.getAttribute("errorMessage")!=null)
		errorMessage = request.getAttribute("errorMessage").toString();
	
	String message=null;
	if(request.getAttribute("message")!=null)
		message = request.getAttribute("message").toString();

	
  PhotoCategoryDAO phc = new PhotoCategoryDAO();
  List<PhotoCategory> photoCategories = phc.getPhotoCategories();
  
  List<Photographer> results = new ArrayList<Photographer>();
  if(request.getAttribute("results")!=null)
    results = (List<Photographer>)request.getAttribute("results");
  
%>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>PhotoCruiters</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="" />
  <meta name="author" content="" />

<style>
.checked {
  color: orange;
}
</style>
  <!-- css -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Serif:400,400italic,700|Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="css2/bootstrap.css" rel="stylesheet" />
  <link href="css2/bootstrap-responsive.css" rel="stylesheet" />
  <link href="css2/fancybox/jquery.fancybox.css" rel="stylesheet">
  <link href="css2/jcarousel.css" rel="stylesheet" />
  <link href="css2/flexslider.css" rel="stylesheet" />
  <link href="css2/style.css" rel="stylesheet" />

    <link href="css2/checkboxes.css" rel="stylesheet" />
    <script src="js2/checkboxes.js"></script>
    <link href="css2/jobs.css" rel="stylesheet" />


  <!-- Theme skin -->
  <link href="skins/default.css" rel="stylesheet" />
  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png" />
  <link rel="shortcut icon" href="ico/favicon.png" />

  <!-- =======================================================
    Theme Name: PhotoCruiters
    Theme URL: https://bootstrapmade.com/flattern-multipurpose-bootstrap-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>
<body>
   
    <div id="wrapper">
  
      <%@include file="header.jsp" %>
      
      <% if(errorMessage!=null && errorMessage!="") { %>
  	    <div class="alert alert-danger">
  			<%=errorMessage%>
  		</div>
      <% } %>
      
      <% if(message!=null && message!="") { %>
  	    <div class="alert alert-success">
  			<%=message%>
  		</div>
      <% } %>
		
		<div class="container theme-showcase" role="main">    
			    	<div class="page-header">
			    		<h4>Φόρμα Αγγελίας</h4>
			    	</div>
			  		<form action="doCreateJob.jsp" method="post">
			  			<div class="row">
			      			<div class="col-25">
			        			<label for="description">Περιγραφή εργασίας</label>
			      			</div>
			      			<div class="col-75">
			        			<textarea id="description" name="description" placeholder="Περιγραφή εργασίας" style="height:60px"></textarea>
			      			</div>
			    		</div>
					
					    <div class="row">
					      <div class="col-25">
					        <label for="date">Ημερομηνία</label>
					      </div>
					      <div class="col-75">
					        <input type="date" id="date" name="date" placeholder="Επιθυμητή ημερομηνία">
					      </div>
					    </div>

					    <div class="row">
					      <div class="col-25">
					        <label for="duration">Διάρκεια</label>
					      </div>
					      <div class="col-75">
					        <input type="number" id="duration" name="duration" placeholder="Σε ώρες">
					      </div>
					    </div>

					    <div class="form-group row">
					        <div class="col-25">
					            <label for="category" class="col-form-label">Κατηγορία</label>
					        </div>
					        <div class="form-control">
					        	<div>
					        		<select name="photocat">
					                  <option value="" selected>Παρακαλώ επιλέξτε...</option>
					                  <% for(PhotoCategory pc : photoCategories) { %>
					                  		<option value="<%=pc.getId() %>"><%=pc.getName()%></option>
					               	  <% } %>
					                </select>
					            </div>
				        	</div>
				    	</div>
			
					    <div class="row">
					      <div class="col-25">
					        <label for="price">Αμοιβή</label>
					      </div>
					      <div class="col-75">
					        <input type="number" step="0.01" min="0" id="price" name="price" placeholder="Αμοιβή">
					      </div>
					    </div>
					    <div class="row">
					      <input type="submit" value="Submit">
					    </div>
					</form>

			<!-- proigoumenes aggelies -->
			<br>
			<h4> Οι αγγελίες μου: </h4>
			<%
   				List<Job> myjobs = new ArrayList<Job>();
			  	JobDAO jd = new JobDAO();
				if(u!=null)
				  	myjobs = jd.getJobsForEmployer(u.getUserid());
			%>
			<br>
				<% if(myjobs.size()>0) { %>
					<table border="1">
						<tr>
							<th>Ημερομηνία εργασίας</th>
							<th>Περιγραφή</th>
							<th>Διάρκεια</th>
							<th>Κατηγορία</th>
							<th>Τιμή</th>
							<th>Κατάσταση</th>
							<th>Ανατέθηκε σε</th>
						</tr>
						<% for(Job j : myjobs) { 
							SimpleDateFormat sd= new SimpleDateFormat("dd/MM/yyyy");
						
						%>
						<tr>
							<td><%=sd.format(j.getJob_date())%></td>
							<td><%=j.getDescription() %></td>
							<td><%=j.getJob_duration() %></td>
							<td><%=j.getRelatedCategory()!=null ? j.getRelatedCategory().getName() : "" %></td>
							<td><%=j.getPrice() %></td>
							<td><%=j.getStatus()==0 ? "Εκκρεμεί" : "Ανατέθηκε" %></td>
							<td><%=j.getPhotographer()!=null ? j.getPhotographer().getEmail() : "" %></td>
							
						</tr>
						<% if(j.getJobsInterested().size() > 0) { %>
								<tr>
									<td colspan="7" style="margin:20px;">
										<h2>Προσφορές:</h2>
										<table border="1">
											<tr>
												<th>Φωτογράφος</th>
												<th>Προσφορά</th>
												<th>Έγκριση Προσφοράς</th>
											<tr>
											<% for(JobInterested ji : j.getJobsInterested()) { %>
												<tr>
													<td><%=ji.getPhotographer().getName() %>&nbsp;<%=ji.getPhotographer().getSurname() %></td>
													<td><%=ji.getPrice() %></td>
													<td>
														<form action="doAcceptOffer.jsp" method="post" id="offerForm<%=ji.getId()%>">
							            					<input type="hidden" name="jobId" value="<%=ji.getJob().getJob_id() %>">
							            					<input type="hidden" name="winner" value="<%=ji.getPhotographer().getUserid() %>">
							            					<a class="btn btn-large btn-theme btn-rounded" href="#" onclick="document.getElementById('offerForm<%=ji.getId()%>').submit();">Αποδοχή Προσφοράς</a>
						            					</form>
													</td>
												</tr>
											<% } %>
										</table>
									</td>
								</tr>
							<% } %>
						<% } %>
					</table>
				<% } else { %>
					<h2>Δεν έχετε δημιουργήσει εργασίες</h2>
				
				<% } %>


<!-- 
					<div class="container1">
					  <p><span>Job category.</span> Hmerominia.</p>
					  <p>Descriptio.n</p>
					  <p>Duration.</p>
					  <p>Price.</p>
					</div>
 -->
		</div>




              <!-- end divider -->
              <section id="bottom">
                <div class="container">
                  <div class="row">
                    <div class="span12">
                      <div class="aligncenter">
                        <div id="twitter-wrapper">
                          <div id="twitter">
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                </section>
                <footer>
                <div class="container">
                  <div class="row">
                    <div class="span3">
                      <div class="widget">
                        <h5 class="widgetheading">Browse pages</h5>
                        <ul class="link-list">
                          <li><a href="#">About our company</a></li>
                          <li><a href="#">Our services</a></li>
                          <li><a href="#">Meet our team</a></li>
                          <li><a href="#">Explore our portfolio</a></li>
                          <li><a href="#">Get in touch with us</a></li>
                        </ul>
                      </div>
                    </div>
                    <div class="span3">
                      <div class="widget">
                        <h5 class="widgetheading">Important stuff</h5>
                        <ul class="link-list">
                          <li><a href="#">Press release</a></li>
                          <li><a href="#">Terms and conditions</a></li>
                          <li><a href="#">Privacy policy</a></li>
                          <li><a href="#">Career center</a></li>
                          <li><a href="#">PhotoCruiters forum</a></li>
                        </ul>
                      </div>
                    </div>
                    <div class="span3">
                      <div class="widget">
                        <h5 class="widgetheading">Get in touch with us</h5>
                        <address>
                          <strong>PhotoCruiters studio, Pte Ltd</strong><br>
                           Eptalofou 7, Mosxato<br>
                           10545 Athens
                        </address>
                        <p>
                          <i class="icon-phone"></i> (210) 5139109-10<br>
                          <i class="icon-envelope-alt"></i> PhotoCruiters@supermail.com
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div id="sub-footer">
                  <div class="container">
                    <div class="row">
                      <div class="span6">
                        <div class="copyright">
                          <p>
                            <span>&copy; PhotoCruiters - All right reserved.</span>
                          </p>
                          <div class="credits">
                            <!--
                              All the links in the footer should remain intact.
                              You can delete the links only if you purchased the pro version.
                              Licensing information: https://bootstrapmade.com/license/
                              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=PhotoCruiters
                            -->
                          </div>
                        </div>
                      </div>
                      <div class="span6">
                        <ul class="social-network">
                          <li><a href="#" data-placement="bottom" title="Facebook"><i class="icon-facebook icon-square"></i></a></li>
                          <li><a href="#" data-placement="bottom" title="Twitter"><i class="icon-twitter icon-square"></i></a></li>
                          <li><a href="#" data-placement="bottom" title="Linkedin"><i class="icon-linkedin icon-square"></i></a></li>
                          <li><a href="#" data-placement="bottom" title="Pinterest"><i class="icon-pinterest icon-square"></i></a></li>
                          <li><a href="#" data-placement="bottom" title="Google plus"><i class="icon-google-plus icon-square"></i></a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
                </footer>
                </div>
                <a href="#" class="scrollup"><i class="icon-chevron-up icon-square icon-32 active"></i></a>
                <!-- javascript
                ================================================== -->
                <!-- Placed at the end of the document so the pages load faster -->
                <script src="js2/jquery.js"></script>
                <script src="js2/jquery.easing.1.3.js"></script>
                <script src="js2/bootstrap.js"></script>
                <script src="js2/jcarousel/jquery.jcarousel.min.js"></script>
                <script src="js2/jquery.fancybox.pack.js"></script>
                <script src="js2/jquery.fancybox-media.js"></script>
                <script src="js2/google-code-prettify/prettify.js"></script>
                <script src="js2/portfolio/jquery.quicksand.js"></script>
                <script src="js2/portfolio/setting.js"></script>
                <script src="js2/jquery.flexslider.js"></script>
                <script src="js2/jquery.nivo.slider.js"></script>
                <script src="js2/modernizr.custom.js"></script>
                <script src="js2/jquery.ba-cond.min.js"></script>
                <script src="js2/jquery.slitslider.js"></script>
                <script src="js2/animate.js"></script>

                <!-- Template Custom JavaScript File -->
                <script src="js2/custom.js"></script>
</body>
