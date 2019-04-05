<div id='cssmenu' >
	<ul>
		<li><a href="<%=request.getContextPath()%>/admin/index"><span>Home</span></a></li>
		<li>
		   <a href="<%=request.getContextPath()%>/admin/problems"><span>Problèmes</span></a>
		   <ul>
		      <li class='has-sub'>
		      	<a href="<%=request.getContextPath()%>/admin/problems"><span>Tous les problèmes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/myproblems"><span>Mes problèmes</span></a>
		      </li>
		   </ul>
		</li>
		<li> <a href='<%=request.getContextPath()%>/admin/users'><span>Membres</span></a></li>
		<li>
		   <a href="#"><span>Archives</span></a>
		   <ul>
		      <li class='has-sub'>
		      	<a href="<%=request.getContextPath()%>/admin/archivemember"><span>Membres</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/archiveproblem"><span>Problèmes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/archivesproblem"><span>Sous Problèmes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/archivesreply"><span>Réponses</span></a>
		      </li>
		   </ul>
		</li>
		<li class='last'><a href="<%=request.getContextPath()%>/logout"><span>Log out</span></a></li>
   	</ul>
</div>