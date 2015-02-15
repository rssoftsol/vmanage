<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<nav class="navbar navbar-default" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
	      			
					<li id="aboutus">
						<a href='${pageContext.request.contextPath}/aboutus'>
							<span class='glyphicon glyphicon-user' ><b> About us</b></span>
						</a>
					</li>
					<li id="contactus">
						<a href='${pageContext.request.contextPath}/contactus'>
							<span class='glyphicon glyphicon-earphone' ><b> Contact us</b></span>
						</a>
					</li>
					<li id="register">
						<a href='${pageContext.request.contextPath}/create.htm'>
							<span class='glyphicon glyphicon-edit' ><b> Registration</b></span>
						</a>
					</li>
					<li id="login">
	      				<a href="${pageContext.request.contextPath}/login"  >
	      					<span class='glyphicon glyphicon-log-in'><b> Login</b></span>
	      				</a>
	      			</li>
		  		</ul>
			</div>
		</div>
	</nav>
<script type="text/javascript">
	var mode= "${menumode}";
	if(mode == "L")
		$('#login').addClass('active');
	else if(mode == "R")
		$('#register').addClass('active');
	else if(mode == "A")
		$('#aboutus').addClass('active');
	else if(mode == "C")
		$('#contactus').addClass('active');
</script>
</html>

