<%@ include file = "taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Complex Problem Management - Admin Space</title>
		<%@ include file = "assets.jsp" %>
	</head>
	
	<body class="whiteBody">
		
		<%@ include file = "menu.jsp" %>
		
		<!-- Ajouter la barre de recherche ici -->
		
		
<!-- Affichage du probl�me -->

		<div class="centeredDiv">
			<div class="rightDiv"></div>
			<div class="leftDiv">
				
				<div class="divReplyTitle" style="margin-top:40px; ">
					<h2> Membre s�lectionn� </h2>
				</div>
				
				<div class="reply">
					 <div class="reply-content">
						<!-- Header -->
						<div class="reply-header">
							<p class="left"># ${u.getIdUser() } </p>
						</div>
						
						<!-- Body -->
						<div class="reply-body">
							<div class="right-div" >
								<p>Nom & Pr�nom :<strong> ${u.getFirstName() } ${u.getLastName() }. </strong><br>
								Adresse Mail : <strong>${u.getMail() }.</strong> </p>
							</div>
						</div>
						
						<!-- Footer -->
						<div class="reply-footer" style="border-top: 1px solid #80bfff;">
							<p class="right">
								<a href="delUser?idUser=${u.idUser }"><i class="material-icons">delete</i></a>
							<p>
						</div>
					</div>
				</div>
				
				<!-- Liste des probl�mes -->
				<div class="divReplyTitle">
					<h2>${nbrpr } Probl�mes </h2>
				</div>
				<c:forEach items="${problems}" var="pr">
					<div class="reply">
						 <div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left" style="font-size: 25px; color: white; font-weight: bold;">${pr.titlePr}</p>
								<p class="right"><strong>$ ${pr.datePr} - # ${pr.idPr}</strong></p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div">
									<p>${pr.bodyPr}</p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">Acc�der au :
									<a style="font-size: 15px;" href="afficheSProblem?idPr=${pr.idPr}"> Sous Probl�mes </a> --
									<a style="font-size: 15px;" href="afficheProblem?idPr=${pr.idPr}">R�ponses</a>
								</p>
								<p class="right">
									<a href="deleteProblem?idPr=${pr.idPr }"><i class="material-icons">delete</i></a>
								<p>
							</div>
						</div>
					</div>
				</c:forEach>
				<br>
				<!-- Sous probl�mes -->
				<div class="divReplyTitle">
					<h2> ${nbrspr } Sous Probl�mes </h2>
				</div>
				<c:forEach items="${sproblems}" var="spr">
					<div class="reply">
						 <div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left" style="font-size: 25px; color: white; font-weight: bold;">${spr.titleSPr}</p>
								<p class="right"> <strong> ${spr.dateSPr} - # ${spr.idSPr}</strong></p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div">
									<p>${spr.bodySPr}</p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">Acc�der au :
									<a style="font-size: 15px;" href="afficheSProblemRep?idSPr=${spr.idSPr }">R�ponses</a>
								</p>
								<p class="right">
									<a href="deleteSProblem?idSPr=${spr.idSPr }"><i class="material-icons">delete</i></a>
								<p>
							</div>
						</div>
					</div>
				</c:forEach>
				
				<br>
				<!-- ----------------------------------------------------Solution---------------------------------------------------- -->
				<div class="divReplyTitle">
					<h2> ${nbrrep }  R�ponses </h2>
				</div>
				
				<c:forEach items="${replies}" var="rep">
					<div class="reply">
						<div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<c:choose>
									<c:when test="${rep.sproblem.idSPr == null }"><p class="left">answered ${rep.dateRep}</p></c:when>
									<c:otherwise><p class="left"><span class="sproblemtitle"><strong>${rep.sproblem.titleSPr }</strong></span> - answered ${rep.dateRep}</p></c:otherwise>
								</c:choose>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="left-div">
								</div>
								<div class="right-div">
									<p>${rep.contentRep}</p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer">
								<p class="right">
									<a href="deleteRepProblem?idRep=${rep.idRep }" ><i class="material-icons">delete</i></a>
								<p>
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