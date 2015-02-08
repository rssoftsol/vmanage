<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<nav class="navbar navbar-default header" style="margin-bottom:0px" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
					<li><a><span>${date}</span></a></li>
					<li><a><span>Welcome ${user}</span></a></li>
					<li id="myprofile"><a href='${pageContext.request.contextPath}/myprofile/edit'><span>My Profile</span></a></li>
					<li id="member"><a href='${pageContext.request.contextPath}/members/browsemembers'><span>Operations</span></a></li>
					<li><a href='${pageContext.request.contextPath}/logout'><span>Logout</span></a></li>
					
		  		</ul>
			</div>
		</div>
	</nav>
<script type="text/javascript">
	var mainmode= "${mainmode}";
	if(mainmode == "MYPROFILE"){
		$('#myprofile').addClass('active');
	}else if(mainmode == "MEMBER"){
		$('#member').addClass('active');
	}
</script>
	
</html>
