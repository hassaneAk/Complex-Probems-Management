<%@ include file = "taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Complex Problems Management</title>
		<%@ include file = "assets.jsp" %>
	</head>
	
	<body class="lightBlue">
		<h1 class="title" style="font-family: 'Almendra SC'; margin-top:5%;">Complex Problem Management</h1>
		<div class="container">  
			<form id="contact" action="checkUser" method="post">
				<h3>Sign IN</h3>
				
				<h4>Username</h4>
				<fieldset>
				 	<input type="text" name="login" id="login" tabindex="1" class="form-control" required="required">
				</fieldset>
				<p class="errors"><c:if test="${loginError != null }"><c:out value="${loginError }" /></c:if></p>
				
				<h4>Password</h4>
				<fieldset>
				 	<input type="password" name="mdp" id="mdp" tabindex="2" class="form-control" required="required">
				</fieldset>
				<p class="errors"><c:if test="${mdpError != null }"><c:out value="${mdpError }" /></c:if></p>
				
				<div class="button">
				 	<button name="submit" type="submit" tabindex="3" style="font-size: 13px;">Get Started</button>
				</div>
				
				<p class="copyright">Don't have an account ?  <a  tabindex="4" href="<%=request.getContextPath()%>/register">Join us Now</a></p>
			</form>
		</div>
		
	    <!-- Bootstrap core JavaScript -->
	    <script src="<%=request.getContextPath()%>/resources/jquery/jquery.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
		
	</body>
	
</html>