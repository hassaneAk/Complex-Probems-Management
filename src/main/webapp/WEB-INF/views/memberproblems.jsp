<%@ include file = "taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Complex Problem Management - Member Space</title>
		<%@ include file = "assets.jsp" %>
	</head>
	<body class="whiteBody">
		
		<%@ include file = "menumember.jsp" %>
		<form  class="search" action="searchProblem" method="post">
			<input type="text" name="keyword" id="keyword" placeholder="Search.." required="required">
			<button name="submit" type="submit"><i class="fa fa-search"></i></button>
		</form>
		<br>
		
		<div class="centeredDiv">
			<div class="leftDiv" style="margin:40px; padding:0;">
			
				<!-- Ajouter un problème -->
				
				<div class="divReplyTitle">
					<p style="margin:0; padding:0; margin-bottom: 5px;">
						<span style="font-size: 25px;color: black;font-weight: bold;color: #80bfff;">Ajouter un nouveau problème</span> 
						<span style="float: right; margin-right: 10px; cursor: pointer; color: #80bfff; font-size: 25px;" ><i id="icon" class="fa fa-sort-desc" onclick="showForm()"></i></span> 
					</p>
				</div>
				<form id="form" action="saveProb" method="post" style="display: none;">	
					<textarea name="titlePr" id="titlePr" class="form-control" style="height: 50px; margin-top:5px;" placeholder="Titre du problème.." required="required"></textarea>	
				 	<textarea name="bodyPr" id="bodyPr" class="form-control" style="height: 50px; margin-top:5px;" placeholder="Description du problème.." required="required"></textarea>
					<button class="btn" type="submit">Ajouter</button>
				</form>
				<br>
				
				<!-- Liste des problèmes -->
				<div class="divReplyTitle">
					<h2>Liste des problèmes </h2>
				</div>
				<c:forEach items="${problems}" var="pr">
					<div class="reply">
						 <div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left" style="font-size: 25px; color: white; font-weight: bold;">${pr.titlePr}</p>
								<p class="right">Added By <strong>${pr.user.getLastName()} ${pr.user.getFirstName()} - ${pr.datePr} - # ${pr.idPr}</strong></p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div">
									<p>${pr.bodyPr}</p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">Accéder au :
									<a style="font-size: 15px;" href="afficheSProblem?idPr=${pr.idPr}"> Sous Problèmes </a> --
									<a style="font-size: 15px;" href="afficheProblem?idPr=${pr.idPr}">Réponses</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
				
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
									
			</div>
		</div>
		
	</body>
</html>