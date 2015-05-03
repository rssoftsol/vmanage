<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-SMSSender</title>
</head>
<body class="header" >
<div id="wrapper">
	<%@ include file="leftmenu.html" %>
	<div class='container-fluid'>
	<form:form name="form" action="${pageContext.request.contextPath}/members/createSmsSenderAction.htm" method="post" commandName="command">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Add SMS Sender</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>Sender ID:</label></div>
				        <div class='col-xs-4'>
				        	<form:input type="text" id='senderId' maxlength="20" path="senderId" class="form-control"/>
				        </div>
				        <div class='col-xs-6'><form:errors path="senderId" cssClass="alert alert-danger error" element="div"/></div>
				    </div>
				    <div class="col-xs-12">
							<div class="col-xs-2"><label>SMS Text:</label></div>
							<div class="col-xs-4"><form:textarea id="smsText" maxlength="160" path="smsText" 
							class="form-control"/></div>
							<div class="col-xs-6"><form:errors path="smsText" cssClass="smsText" element="div" /></div>
					</div>
					<div>
				        <div class='col-xs-offset-10'>
				        	<input type="submit" value="Save" id='save' class="btn btn-primary">
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
