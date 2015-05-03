<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>
<%@include file="menu.jsp" %>

<title>Intimator-Password Change</title>
</head>
<script type="text/javascript">
function checkPassword(t){
	if(t.value!=document.getElementById('password').value){
		t.value = '';
		alert('Password doesn`t match');
	}
}

</script>
<body class="header" >
<div id="wrapper">
	<div class='container-fluid'>
	<form:form name="form" action="${pageContext.request.contextPath}/passwordchangeAction.htm" method="post">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Password Change</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>Old password:</label></div>
				        <div class='col-xs-4'>
				        	<input type="password" id='oldPassword' maxlength="20" name="oldPassword" 
				        	class="form-control" required="required"/>
				        </div>
				    </div>
				    <div class="col-xs-12">
							<div class="col-xs-2"><label>New password:</label></div>
							<div class="col-xs-4">
								<input type="password" id='newPassword' maxlength="20" name="newPassword" 
				        	class="form-control" required="required"/>
							</div>
					</div>
					<div class="col-xs-12">
							<div class="col-xs-2"><label>Confirm password:</label></div>
							<div class="col-xs-4">
								<input type="password" id='confirmpassword' maxlength="20" name="confirmpassword" 
				        	class="form-control" required="required" onblur="checkPassword(this)"/>
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
