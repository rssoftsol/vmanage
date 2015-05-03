<html>

<%@include file="style/style.jsp" %>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>

<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<script type="text/javascript">
$('#sms').addClass('active');
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intimator-My Offer</title>
</head>
<body class="header">
	<!-- <div class="container" id="uploading"><b>Sending....</b></div> -->
	
	<form:form name="form" action="${pageContext.request.contextPath}/members/myOfferAction.htm" 
		method="post">
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading"><h3 class="panel-title">Broadcast Offer</h3></div>
				<div class="panel-body">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-4'><label>Send SMS to:</label></div>
				        <div class='col-xs-6'>
					        <select id='smsto' name="smsTo" class="form-control">
					        	<option value="0" selected="selected">Only to Members whose membership is expired</option>
					        	<option value="1">All members</option>
					        </select>
				        </div>
				    </div>
			        
			        <div class="col-xs-12">
						<div class="col-xs-4"><label>SMS Text:</label></div>
						<div class="col-xs-4">
							<textarea id="smsText" maxlength="160" name="smsText"></textarea>
						</div>
					</div>
					
					<div class='col-xs-offset-10'>
				        	<input type="submit" value="Save" id='save' class="btn btn-primary">
				    </div>
			    </div>
		    </div>
		</div>
		
	  </form:form>
</body>
</html>
