<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@include file="menu.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-SMSSender</title>
</head>
<body class="header" >

	<form:form name="form" action="${pageContext.request.contextPath}/members/createSmsSenderAction.htm" method="post" commandName="command">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Add SMS Sender</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>Sender ID:</label></div>
				        <div class='col-xs-4'>
				        	<form:input type="text" id='id' maxlength="20" path="id" class="form-control"/>
				        	
				        </div>
				        <div class='col-xs-2'>
				        	<input type="submit" value="Save" id='save' class="btn btn-primary">
				        </div>
				        <div class='col-xs-4'><form:errors path="id" cssClass="alert alert-danger error" element="div"/></div>
			        </div>
			        
			    </div>
		    </div>
		</div>
	  </form:form>
</body>
</html>
