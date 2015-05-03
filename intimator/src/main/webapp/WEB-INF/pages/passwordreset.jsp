<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-Password reset</title>
</head>
<body class="header" >
<div id="wrapper">
	<%@ include file="leftmenu.html" %>
	<div class='container-fluid'>
	<form:form name="form" action="${pageContext.request.contextPath}/admin/passwordResetAction.htm" method="post">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Password Reset</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>User Name:</label></div>
				        <div class='col-xs-4'>
				        	<input type="text" id='username' maxlength="20" name="username" 
				        	class="form-control" required="required"/>
				        </div>
				    </div>
				    <div class="col-xs-12">
							<div class="col-xs-2"><label>Password:</label></div>
							<div class="col-xs-4">
								<input type="password" id='password' maxlength="20" name="password" 
				        	class="form-control" required="required"/>
							</div>
					</div>
					<div>
				        <div class='col-xs-offset-10'>
				        	<input type="submit" value="Reset" id='reset' class="btn btn-primary">
				        </div>
			    	</div>
			    </div>
		    </div>
		</div>
	  </form:form>
	  </div>
</div>
</body>
</html>
