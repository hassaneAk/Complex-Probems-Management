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
		<c:choose>
			<c:when test="${archive==true }">
				<form  class="search" action="searchArchiUser" method="post">
					<input type="text" name="keyword" id="keyword" placeholder="Checher un membre.." required="required">
					<button name="submit" type="submit"><i class="fa fa-search"></i></button>
				</form>
			</c:when>
			<c:otherwise>
				<form  class="search" action="searchUser" method="post">
					<input type="text" name="keyword" id="keyword" placeholder="Checher un membre.." required="required">
					<button name="submit" type="submit"><i class="fa fa-search"></i></button>
				</form>
			</c:otherwise>
		</c:choose>
		
		<br>
		<div class="centeredDiv">
			<div class="leftDiv" style="margin:40px; padding:0;">
				<div class="divReplyTitle">
					<h2>${members.size() } membres <c:if test="${archive==true }">archivés</c:if> </h2>
				</div>
				<c:forEach items="${members}" var="u">
					<div class="reply">
						 <div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left"># ${u.getIdUser() } </p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div" >
									<p>Nom & Prénom :<strong> ${u.getFirstName() } ${u.getLastName() }. </strong><br>
									Adresse Mail : <strong>${u.getMail() }.</strong> </p>
								</div>
							</div>
							
							<!-- Footer -->
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">
									<a style="font-size: 15px;" href="userdetail?idUser=${u.getIdUser() }"> Plus de détail </a> 
								</p>
								<c:if test="${archive==false }">
									<p class="right">
										<a href="delUser?idUser=${u.idUser }"><i class="material-icons">delete</i></a>
									<p>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
	</body>
</html>