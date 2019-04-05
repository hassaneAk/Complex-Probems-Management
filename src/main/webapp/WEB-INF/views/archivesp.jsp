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
		
		<form  class="search" action="searchArchiSProblem" method="post">
			<input type="text" name="keyword" id="keyword" placeholder="Search.." required="required">
			<button name="submit" type="submit"><i class="fa fa-search"></i></button>
		</form>
		<br>
		
		<div class="centeredDiv">
			<div class="rightDiv"></div>
			<div class="leftDiv">
				
				<!-- Sous problèmes -->
				<div class="divReplyTitle">
					<h2>${sproblems.size() } sous problèmes archivés </h2>
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
									<p>Problème Parent : <strong>${spr.problem.getTitlePr() }</strong></p>
									<p>Description du sous problème :<strong>${spr.bodySPr}</strong></p>
								</div>
							</div>
							
							<div class="reply-footer" style="border-top: 1px solid #80bfff;">
								<p class="left" style="color: #80bfff; padding-left: 10px; ">Accéder au :
									<a style="font-size: 15px;" href="repOfArchiSpr?idSPr=${spr.idSPr }">Réponses</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
		
	</body>
</html>