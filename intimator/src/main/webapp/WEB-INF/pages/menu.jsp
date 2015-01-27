<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<nav class="navbar navbar-default header" style="margin-bottom:0px" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
					<li><a><span>${date}</span></a></li>
					<li><a><span>Welcome ${user}</span></a></li>
					<li><a href='/SpringMVC/logout'><span>Logout</span></a></li>
		  		</ul>
			</div>
		</div>
	</nav>
</html>
