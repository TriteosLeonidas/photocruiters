<%@page import="photocruiters.models.City"%>
<%@page import="java.util.List"%>
<%@page import="photocruiters.daos.CityDAO"%>
<%@page import="photocruiters.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	CityDAO cd = new CityDAO();
	List<City> cities = cd.getCities();
	User u = null;
	u = (User)session.getAttribute("loggedInUser");
%>

<%! 
	public String isActivePage(String pageName) {
		if(this.getClass().getSimpleName().replaceAll("_",".").contains(pageName))
			return "active";
		else
			return "";
	}

	
%>

<!-- start header -->
    <header>
      <div class="container ">

		<% if(u!=null) { %>

        <div class="row nomargin">
          <div class="span12">
            <div class="headnav">
            	Συνδεδεμένος ως <%=u.getName() %>&nbsp;&nbsp;
            	<a href="<%=request.getContextPath() %>/doLogout.jsp"><span class="glyphicon glyphicon-log-out"></span> Sign out</a>
         <% } else { %>   
         
         	<div class="row nomargin">
          <div class="span12">
            <div class="headnav">
         
              <ul>
                <li><a href="#mySignup" data-toggle="modal"><i class="icon-user"></i> Εγγραφή</a></li>
                <li><a href="#mySignin" data-toggle="modal">Είσοδος</a></li>
              </ul>
            </div>
            
         <% } %>
         
         
            <!-- Signup Modal -->
            <div id="mySignup" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="mySignupModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="mySignupModalLabel">Create an <strong>account</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" action="doRegister.jsp" method="post">
                  <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                      <input type="text" id="name" name="name" placeholder="Name" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="surname">Surname</label>
                    <div class="controls">
                      <input type="text" id="surname" name="surname" placeholder="Surname" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="address">Address</label>
                    <div class="controls">
                      <input type="text" id="address" name="address" placeholder="Address" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputEmail">Email</label>
                    <div class="controls">
                      <input type="text" id="inputEmail" name="email" placeholder="Email" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputMobile">Mobile Phone</label>
                    <div class="controls">
                      <input type="text" id="inputMobile" name="mobile" placeholder="Mobile Phone" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="city">City</label>
                    <div class="controls">
                      <select name="city">
                      	<% for(City c : cities) { %>
                      		<option value="<%=c.getId()%>"><%=c.getName() %></option>
                      	<% } %>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="role">Role</label>
                    <div class="controls">
                      <select name="role">
                        <option value="1"><b>Photographer</b></option>
                        <option value="2">Employer</option>
                      </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputCV">Resume</label>
                    <div class="controls">
                      <textarea id="inputCV" name="cv"></textarea>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputSignupPassword">Password</label>
                    <div class="controls">
                      <input type="password" id="inputSignupPassword" name="password" placeholder="Password" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputSignupPassword2">Confirm Password</label>
                    <div class="controls">
                      <input type="password" id="inputSignupPassword2" name="confirm" placeholder="Password" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn">Sign up</button>
                    </div>
                    <p class="aligncenter margintop20">
                      Already have an account? <a href="#mySignin" data-dismiss="modal" aria-hidden="true" data-toggle="modal">Sign in</a>
                    </p>
                  </div>
                </form>
              </div>
            </div>
            <!-- end signup modal -->
            <!-- Sign in Modal -->
            <div id="mySignin" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="mySigninModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="mySigninModalLabel">Login to your <strong>account</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" action="doLogin.jsp" method="post">
                  <div class="control-group">
                    <label class="control-label" for="inputText">Username</label>
                    <div class="controls">
                      <input type="text" id="inputText" placeholder="Username" name="username">
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputSigninPassword">Password</label>
                    <div class="controls">
                      <input type="password" id="inputSigninPassword" placeholder="Password" name="password">
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn">Sign in</button>
                    </div>
                    <p class="aligncenter margintop20">
                      Forgot password? <a href="#myReset" data-dismiss="modal" aria-hidden="true" data-toggle="modal">Reset</a>
                    </p>
                  </div>
                </form>
              </div>
            </div>
            <!-- end signin modal -->
            <!-- Reset Modal -->
            <div id="myReset" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="myResetModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="myResetModalLabel">Reset your <strong>password</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal">
                  <div class="control-group">
                    <label class="control-label" for="inputResetEmail">Email</label>
                    <div class="controls">
                      <input type="text" id="inputResetEmail" placeholder="Email">
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn">Reset password</button>
                    </div>
                    <p class="aligncenter margintop20">
                      We will send instructions on how to reset your password to your inbox
                    </p>
                  </div>
                </form>
              </div>
            </div>
            <!-- end reset modal -->
          </div>
        </div>
        <div class="row">
          <div class="span4">
            <div class="logo">
              <a href="<%=request.getContextPath() %>/index.jsp"><img src="img/logo.png" alt="" class="logo" /></a>
            </div>
          </div>
          <div class="span8">
            <div class="navbar navbar-static-top">
              <div class="navigation">
                <nav>
                  <ul class="nav topnav">
                    <!-- Case 1: Unregistered user -->
	                	<% if(u==null) { %> 
		                    <li class="<%=isActivePage("index.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/index.jsp"><p style="font-size:15px">Αρχικη</p></a>
		                    </li>
		                    <li class="<%=isActivePage("phsearch.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/phsearch.jsp"><p style="font-size:15px">Αναζητηση φωτογραφων</p></a>
		                    </li>
		                    <li class="<%=isActivePage("jobssearch.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/jobssearch.jsp"><p style="font-size:15px">Αναζητηση εργασιων</p></a>
		                    </li>
		                    <li class="<%=isActivePage("about.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/about.jsp"><p style="font-size:15px">Σχετικα με εμας</p></a>
		                    </li>
	                    <% } %>
                    <!-- End Case 1 -->
                    
                    <!-- Case 2: Photographer -->
	                    <% if(u!=null && u.getRole()==1) { %>
		                    <li class="<%=isActivePage("index.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/index.jsp"><p style="font-size:15px">Αρχικη</p></a>
		                    </li>
		                    <li class="<%=isActivePage("profilePhotographer.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/profilePhotographer.jsp?id=<%=u.getUserid()%>"><p style="font-size:15px">Το Προφιλ μου</p></a>
		                    </li>
		                    <li class="<%=isActivePage("jobssearch.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/jobssearch.jsp"><p style="font-size:15px">αναζητηση εργασιων</p></a>
		                    </li>
		                    <li class="<%=isActivePage("about.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/about.jsp"><p style="font-size:15px">σχετικα με εμας</p></a>
		                    </li>
		                 <% } %>
                    <!-- End Case 1 -->
                    
                    <!-- Case 3: Employer -->
	                    <% if(u!=null && u.getRole()==2) { %>
		                    <li class="<%=isActivePage("index.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/index.jsp"><p style="font-size:15px">Αρχικη</p></a>
		                    </li>
		                    <li class="<%=isActivePage("phsearch.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/phsearch.jsp"><p style="font-size:15px">Αναζητηση φωτογραφων</p></a>
		                    </li>
		                    <li class="<%=isActivePage("myjobs.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/myjobs.jsp"><p style="font-size:15px">Οι εργασιες μου</p></a>
		                    </li>
		                    <li class="<%=isActivePage("about.jsp")%>">
		                      <a href="<%=request.getContextPath() %>/about.jsp"><p style="font-size:15px">σχετικα με εμας</p></a>
		                    </li>
	                    <% } %>
                    <!-- End Case 1 -->
                    
                  </ul>
                </nav>
              </div>
              
              
              
              <!-- end navigation -->
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- end header -->