<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-Notification Service</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
 	$("#balance").ForceNumericOnly();
 	$("#clubid").ForceNumericOnly();
 	
 	$('#manualReminder').on( 'click', function () {
 		if(confirm("Are you sure you want to send manual reminder!")){
  	  		$('form[name=form]').attr('action','${pageContext.request.contextPath}/admin/sendManualReminder.htm');
  	  		$('form[name=form]').submit();
 		}
  	});
 	$('#dailyReminder').on( 'click', function () {
 		if(confirm("Are you sure you want to run daily reminder!")){
  	  		$('form[name=form]').attr('action','${pageContext.request.contextPath}/admin/sendDailyReminder.htm');
  	  		$('form[name=form]').submit();
 		}
  	});
});
</script>
<body class="header" >
<!-- Sidebar -->
<div id="wrapper">
        <%@ include file="leftmenu.html" %>
        <!-- /#sidebar-wrapper -->
   <div class='container-fluid'>
	<form:form name="form" action="${pageContext.request.contextPath}/admin/sendManualReminder.htm" method="post">
		
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">Run Notification Service</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Club Id:</label></div>
				        <div class='col-xs-3'>
				        	<input type="text" id='clubId' maxlength="20" name="clubId" class="form-control"/>
				        </div>
				    </div>
				    <div class='col-offset-6 pull-right'>
				        	<input type="button" value="Run Daily Reminder" id='dailyReminder' class="btn btn-primary">
				        	<input type="button" value="Manual Reminder" id='manualReminder' class="btn btn-primary">
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
