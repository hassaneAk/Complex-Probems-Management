<%@ include file = "taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Complex Problem Management - Member Space</title>
		<%@ include file = "assets.jsp" %>
	</head>
	
	<body>
	
		<%@ include file = "menumember.jsp" %>
		<div class="centeredDiv">
			<div class="rightDiv"></div>
			<div class="leftDiv">
				
				<div class="divReplyTitle" style="margin-top:40px; ">
					<h2> Membre connecté </h2>
				</div>
				
				<div class="reply">
					 <div class="reply-content">
						<!-- Header -->
						<div class="reply-header">
							<p class="left"># ${loggedUser.getIdUser() } </p>
						</div>
						
						<!-- Body -->
						<div class="reply-body">
							<div class="right-div" >
								<p>Nom & Prénom :<strong> ${loggedUser.getFirstName() } ${loggedUser.getLastName() }. </strong><br>
								Adresse Mail : <strong>${loggedUser.getMail() }.</strong> </p>
							</div>
						</div>
						
						<!-- Footer -->
						<div class="reply-footer" style="border-top: 1px solid #80bfff;">
							<p class="footerContentRight">
								<a onclick="showForm()" >Modifier mes informations <i id="icon" class="fa fa-sort-desc"></i> </a> 
							</p>
							<br>
							<div id="form"  style="display: none;" class="center">
								<form action="updateUser" method="post">
									<p class="footerContentLeft" >First name</p>
									<input type="text" name="firstname" id="firstname" value="${loggedUser.getFirstName() }" class="form-control" required="required">
									<p class="footerContentLeft" >Last name</p>
									<input type="text" name="lastname" id="lastname" value="${loggedUser.getLastName() }" class="form-control" required="required">
									<p class="footerContentLeft" >Mail</p>
									<input type="email" name="mail" id="mail" value="${loggedUser.getMail() }" class="form-control" required="required">
									<button class="btn" type="submit">Enregistrer</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script>
			function showForm() {
			    var x = document.getElementById("form");
			    var ic = document.getElementById("icon");
			    if (x.style.display === "none") {
			    	ic.className = "fa fa-sort-asc";
			        x.style.display = "block";
			    } else {
			    	ic.className = "fa fa-sort-desc";
			        x.style.display = "none";
			    }
			}
		</script>
		
	</body>
</html>