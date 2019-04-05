<%@ include file = "taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Complex Problem Management - Admin Space</title>
		<%@ include file = "assets.jsp" %>
	</head>
	<body class="whiteBody">
		
		<%@ include file = "menumember.jsp" %>
		<form  class="search" action="searchSProblem" method="post">
			<input type="hidden" name="idPr" id="idPr" value="${problem.idPr}">
			<input type="text" name="keyword" id="keyword" placeholder="Search.." required="required">
			<button name="submit" type="submit"><i class="fa fa-search"></i></button>
		</form>
		<br>
		<div class="centeredDiv">
			<div class="rightDiv"></div>
			<div class="leftDiv">
			
			<!-- Problème -->
				<div class="divReplyTitle" style="margin-top:40px; ">
					<h2> Problème sélectionné </h2>
				</div>
				
				<div class="reply">
					 <div class="reply-content">
						<!-- Header -->
						<div class="reply-header">
							<p class="left" style="font-size: 25px; color: white; font-weight: bold;">${problem.titlePr}</p>
							<p class="right">Added By <strong>${problem.user.getLastName()} ${problem.user.getFirstName()} - ${problem.datePr} - # ${problem.idPr}</strong></p>
						</div>
						
						<!-- Body -->
						<div class="reply-body">
							<div class="right-div">
								<p>${problem.bodyPr}</p>
							</div>
						</div>
						
						<!-- Footer -->
						<div class="reply-footer" style="border-top: 1px solid #80bfff;">
							<p class="left" style="color: #80bfff; padding-left: 10px; ">Accéder au :
								<a style="font-size: 15px;" href="afficheProblem?idPr=${problem.idPr}">Réponses</a>
							</p>
						</div>
					</div>
				</div>
				
				<br>
			
				<!-- Ajouter un sous problème -->
				
				<div class="divReplyTitle">
					<p style="margin:0; padding:0; margin-bottom: 5px;">
						<span style="font-size: 25px;color: black;font-weight: bold;color: #80bfff;">Ajouter un sous problème</span> 
						<span style="float: right; margin-right: 10px; cursor: pointer; "><i id="icon" class="fa fa-sort-desc" style="color:#80bfff; font-size:25px; margin:0; padding:0; " onclick="showForm()"></i></span> 
					</p>
				</div>
				<form id="form" action="saveSProb" method="post" style="display: none;">
					<input type="hidden" name="idPr" id="idPr" value="${problem.idPr }">	
					<textarea name="titleSPr" id="titleSPr" class="form-control" style="height: 50px; margin-top:5px;" placeholder="Titre du sous problème.." required="required"></textarea>	
				 	<textarea name="bodySPr" id="bodySPr" class="form-control" style="height: 50px; margin-top:5px;" placeholder="Description du sous problème.." required="required"></textarea>
					<button class="btn" type="submit">Ajouter</button>
				</form>
				<br>
				
				<!-- Sous problèmes -->
				<div class="divReplyTitle">
					<h2>Liste des sous problèmes </h2>
				</div>
				<c:forEach items="${sproblems}" var="spr">
					<div class="reply">
						 <div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left" style="font-size: 25px; color: white; font-weight: bold;">${spr.titleSPr}</p>
								<p class="right">Added By <strong>${spr.user.getLastName()} ${spr.user.getFirstName()} - ${spr.dateSPr} - # ${spr.idSPr}</strong></p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div">
									<p>${spr.bodySPr}</p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">Accéder au :
									<a style="font-size: 15px;" href="afficheSProblemRep?idSPr=${spr.idSPr }">Réponses</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
		
		<!-- Script for form show/hide -->
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