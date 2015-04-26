<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-SMSSender</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
 	$("#balance").ForceNumericOnly();
});
</script>
<body class="header" >

	<form:form name="form" action="${pageContext.request.contextPath}/admin/updateSmsBalanceAction.htm" method="post" commandName="command">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Add SMS Sender</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Club Id:</label></div>
				        <div class='col-xs-3'>
				        	<form:input type="text" id='clubid' maxlength="20" path="clubId" class="form-control"/>
				        </div>
				        <div class='col-xs-5 col-xs-offset-1'><form:errors path="clubId" cssClass="alert alert-danger error" element="div"/></div>
				    </div>
				    <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Add Balance of:</label></div>
				        <div class='col-xs-3'>
				        	<form:input type="text" id='balance' maxlength="5" path="balance" class="form-control"/>
				        </div>
				        <div class='col-xs-1'>SMS</div>
				        <div class='col-xs-5'><form:errors path="balance" cssClass="alert alert-danger error" element="div"/></div>
				    </div>
				    <div class='col-xs-12'>
				        <div class='col-xs-2'>
				        	<input type="submit" value="Save" id='save' class="btn btn-primary">
				        </div>
			        </div>
			        
			    </div>
		    </div>
		</div>
	  </form:form>
</body>
</html>
