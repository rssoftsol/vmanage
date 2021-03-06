<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<nav class="navbar navbar-default" style="margin-bottom:0px" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
					<li><a><span class="glyphicon glyphicon-time"> ${date}</span></a></li>
					<li><a><span>Welcome ${user}</span></a></li>
					<li id="myprofile" data-toggle="tooltip" data-placement="bottom" 
	      			title="View or edit your details here">
						<a href='${pageContext.request.contextPath}/myprofile/view'>
							<span class="glyphicon glyphicon-user"> MyProfile</span>
						</a>
					</li>
					<li id="member" data-toggle="tooltip" data-placement="bottom" 
	      			title="Perform member operations here, like add,change,remove,search">
						<a href='${pageContext.request.contextPath}/members/browsemembers' >
							<span class="glyphicon glyphicon-edit"> Operations</span>
						</a>
					</li>
					<li>
						<a href='${pageContext.request.contextPath}/j_spring_security_logout'>
							<span class="glyphicon glyphicon-log-out"> Logout</span>
						</a>
					</li>
					
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
