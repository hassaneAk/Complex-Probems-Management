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
			<form id="contact" action="saveUser" method="post">
				<h3>Sign UP</h3>
				
				<h4>First Name</h4>
				<fieldset>
				 	<input type="text" name="firstname" id="firstname" tabindex="1" class="form-control" required="required">
				</fieldset>
				<p class="errors"><c:if test="${userExistError != null }"><c:out value="${userExistError }" /></c:if></p>
				
				<h4>Last Name</h4>
				<fieldset>
				 	<input type="text" name="lastname" id="lastname" tabindex="2" class="form-control" required="required">
				</fieldset>
				
				<h4>Mail</h4>
				<fieldset>
				 	<input type="text" name="mail" id="mail" tabindex="3" class="form-control" required="required">
				</fieldset>
				
				<h4>UserName</h4>
				<fieldset>
				 	<input type="text" name="login" id="login" tabindex="4" class="form-control" required="required">
				</fieldset>
				<p class="errors"><c:if test="${loginExistError != null }"><c:out value="${loginExistError }" /></c:if></p>
				
				<h4>Password</h4>
				<fieldset>
				 	<input type="password" name="mdp" id="mdp" tabindex="5" class="form-control" required="required">
				</fieldset>
				
				<h4>Retype password</h4>
				<fieldset>
				 	<input type="password" name="cmdp" id="cmdp" tabindex="6" class="form-control" required="required">
				</fieldset>
				<p class="errors"><c:if test="${mdpMatchError != null }"><c:out value="${mdpMatchError }" /></c:if></p>
				
				<div class="button"> 
				 	<button name="submit" type="submit" tabindex="7">Save</button>
				</div>
				
				<p class="copyright">Already have an account ? <a tabindex="8" href="<%=request.getContextPath()%>/login">Get Started</a></p>
			</form>
		</div>
		
	    <!-- Bootstrap core JavaScript -->
	    <script src="<%=request.getContextPath()%>/resources/jquery/jquery.min.js"></script>
	    <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	</body>
</html>