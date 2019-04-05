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

		<div class="centeredDiv">
			<div class="rightDiv"></div>
			<div class="leftDiv">
				<br>
				<!-- ----------------------------------------------------Solution---------------------------------------------------- -->
				<div class="divReplyTitle">
					<h2> ${replies.size() } réponses archivées </h2>
				</div>
				
				<c:forEach items="${replies}" var="rep">
					<div class="reply">
						<div class="reply-content">
							<!-- Header -->
							<div class="reply-header">
								<p class="left">answered ${rep.dateRep}</p>
								<p class="right">Added By <strong>${rep.user.getFirstName()} ${rep.user.getLastName()}</strong></p>
							</div>
							
							<!-- Body -->
							<div class="reply-body">
								<div class="right-div">
									<p><c:if test="${rep.sproblem.idSPr != null }"> Sous problème <strong>${rep.sproblem.titleSPr }</strong> - </c:if>  Problème parent : <strong>${rep.problem.titlePr }</strong> </p>
									<p>Description de la réponse : <strong>${rep.contentRep}</strong></p>
								</div>
							</div>
	
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		
	</body>
</html>