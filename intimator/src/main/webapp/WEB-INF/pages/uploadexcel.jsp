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

<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<script type="text/javascript">
$('#addMember').addClass('active');
$(document).ready(function() {
	$('#uploading').hide();
    $('#downloadExcel').on( 'click', function () {
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/mymembers.xls');
  	  	$('form[name=form]').submit();
  	});
    $('#singleupload').on( 'click', function () {
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/member/ADD');
  	  	$('form[name=form]').attr('method','GET');
  	  	$('form[name=form]').submit();
  	});
    $('#upload').on( 'click', function () {
    	$('#uploading').show();
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/member/uploadAction');
  	  	$('form[name=form]').submit();
  	});
} );



</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members Detail</title>
</head>
<body class="body" background="<c:url value="/resources/img/body-bg.jpg" />">
	<div class="container" id="uploading"><b>Uploading....</b></div>
	<form:form name="form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/members/member/uploadAction" method="post">
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading"><h3 class="panel-title">Excel upload</h3></div>
				<div class="panel-body">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-2'><label>File:</label></div>
				        <div class='col-xs-8'><input type="file" id='file' name="file" class="form-control"/></div>
				        <div class='col-xs-2'>
				        	<input type="button" value="Upload" id='upload' hidden='true'  class="btn btn-primary">
				        </div>
			        </div>
			    </div>
			    <div class="panel-footer">Click<a href="#" id="singleupload"><b> here </b> </a> to add single member at a time</div>
		    </div>
		</div>
		<div class=" container col-xs-6 col-xs-offset-4">Please<a href="#" id="downloadExcel"><b> Download </b> </a>valid excel format for uploading</div>
	  </form:form>
</body>
</html>
