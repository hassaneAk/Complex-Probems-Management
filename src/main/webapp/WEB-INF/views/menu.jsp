<div id='cssmenu' >
	<ul>
		<li><a href="<%=request.getContextPath()%>/admin/index"><span>Home</span></a></li>
		<li>
		   <a href="<%=request.getContextPath()%>/admin/problems"><span>Probl�mes</span></a>
		   <ul>
		      <li class='has-sub'>
		      	<a href="<%=request.getContextPath()%>/admin/problems"><span>Tous les probl�mes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/myproblems"><span>Mes probl�mes</span></a>
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
		      		<a href="<%=request.getContextPath()%>/admin/archiveproblem"><span>Probl�mes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/archivesproblem"><span>Sous Probl�mes</span></a>
		      </li>
		      <li class='has-sub'>
		      		<a href="<%=request.getContextPath()%>/admin/archivesreply"><span>R�ponses</span></a>
		      </li>
		   </ul>
		</li>
		<li class='last'><a href="<%=request.getContextPath()%>/logout"><span>Log out</span></a></li>
   	</ul>
</div>