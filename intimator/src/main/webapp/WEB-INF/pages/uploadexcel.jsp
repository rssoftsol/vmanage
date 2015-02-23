<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>
<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">


<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<%@ include file="popup.html" %>

<script type="text/javascript">
if("${popupMessage}"!=''){
	$('#popup').modal('show');
}
//alert('hi');
/* var addMode = "AM";
var modifyMode = "MM";
var deleteMode = "DM";
 */
var mode = "${mode}";
var get_mem_button_visibility = (mode != "ADD");
var text_field_visibility = (mode != "DELETE");
$(document).ready(function() {
    $('#example1').datepicker({
        format: "dd/mm/yyyy"
    });
    if(!get_mem_button_visibility){
    	$("#getMemBtn").hide();
    }
    if(!text_field_visibility){
    	$( "#name" ).prop( "readonly", true );
    	$( "#phone" ).prop( "readonly", true );
    	$( "#example1" ).prop( "readonly", true );
    }
    $('#getMemBtn').on( 'click', function () {
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/view/${mode}/'+document.getElementById('memid').value);
  	  	$('form[name=form]').submit();
  	});
    $("#phone").ForceNumericOnly();
} );



</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members Detail</title>
</head>
<body class="body" background="<c:url value="/resources/img/body-bg.jpg" />">

	<form:form name="form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/members/member/uploadAction" method="post">
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading"><h3 class="panel-title">Excel upload</h3></div>
				<div class="panel-body">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>File:</label></div>
				        <div class='col-xs-8'><input type="file" id='file' name="file" class="form-control"/></div>
				        <div class='col-xs-2'>
				        	<input type="submit" value="Upload" id='upload' hidden='true'  class="btn btn-primary">
				        </div>
			        </div>
			        
			    </div>
		    </div>
		</div>
	  </form:form>
</body>
</html>
