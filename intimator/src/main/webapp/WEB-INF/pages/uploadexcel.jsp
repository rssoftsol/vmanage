<html>

<%@include file="style/style.jsp" %>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>

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
<title>Intimator-Upload Members Detail</title>
</head>
<body class="header">
	<div class="container" id="uploading"><b>Uploading....</b></div>
	
	<form:form name="form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/members/member/uploadAction" method="post">
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading"><h3 class="panel-title">Excel upload</h3></div>
				<div class="panel-body">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-4'><label>Date Format used:</label></div>
				        <div class='col-xs-6'><select id='dateFormat' name="dateFormat" class="form-control">
				        	<option value="0" selected="selected">--select--</option>
				        	<option value="dd-MM-yyyy">dd-mm-yyyy (eg. 15-1-2015)</option>
				        	<option value="MM-dd-yyyy">mm-dd-yyyy (eg. 1-15-2015)</option>
				        	<option value="yyyy-mm-dd">yyyy-mm-dd (eg. 2015-1-15)</option>
				        	
				        	<option value="dd-MM-yy">dd-mm-yy (eg. 15-1-15)</option>
				        	<option value="MM-dd-yy">mm-dd-yy (eg. 15-1-2015)</option>
				        	<option value="yy-MM-dd">yy-mm-dd (eg. 15-1-15)</option>
				        	
				        	<option value="dd/MM/yyyy">dd/mm/yyyy (eg. 15/1/2015)</option>
				        	<option value="MM/dd/yyyy">mm/dd/yyyy (eg. 1/15/2015)</option>
				        	<option value="yyyy/MM/dd">yyyy/mm/dd (eg. 2015/1/15)</option>
				        	
				        	<option value="dd-MM-yy">dd-mm-yy (eg. 15-1-15)</option>
				        	<option value="MM-dd-yy">mm-dd-yy (eg. 1-15-15)</option>
				        	<option value="yy-MM-dd">yy-mm-dd (eg. 15-1-15)</option>
				        	
				        	<option value="dd-MMMM-yyyy">dd-mmmm-yyyy (eg. 15-jan-2015)</option>
				        	<option value="MMMM-dd-yyyy">mmmm-dd-yyyy (eg. jan-15-2015)</option>
				        	<option value="yyyy-MMMM-dd">yyyy-mmmm-dd (eg. 2015-jan-1)</option>
				        	
				        	<option value="dd-MMMM-yy">dd-mmmm-yy (eg. 15-jan-15)</option>
				        	<option value="MMMM-dd-yy">mmmm-dd-yy (eg. jan-15-15)</option>
				        	<option value="yy-MMMM-dd">yy-mmmm-dd (eg. 15-jan-15)</option>
				        	
				        	<option value="dd/MMMM/yyyy">dd/mmmm/yyyy (eg. 15/jan/2015)</option>
				        	<option value="MMMM/dd/yyyy">mmmm/dd/yyyy (eg. jan/15/2015)</option>
				        	<option value="yyyy/MMMM/dd">yyyy/mmmm/dd (eg. 15/jan/15)</option>
				        	
				        	<option value="dd-MMMM-yy">dd-mmmm-yy (eg. 15 jan 15)</option>
				        	<option value="MMMM-dd-yy">mmmm-dd-yy (eg. jan 15 15)</option>
				        	<option value="yy-MMMM-dd">yy-mmmm-dd (eg. 15 jan 15)</option>
				        	
				        	<option value="dd/MMMM/yyyy">dd/mmmm/yyyy (eg. 15 jan 2015)</option>
				        	<option value="dd/MMMM/yyyy">mmmm/dd/yyyy (eg. jan 15 2015)</option>
				        	<option value="dd/MMMM/yyyy">yyyy/mmmm/dd (eg. 15 jan 15)</option>
				        	
				        </select>
				        </div>
				    </div>
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-4'><label>File:</label></div>
				        <div class='col-xs-6'><input type="file" id='file' name="file" class="form-control" required="required"/></div>
						<div class='col-xs-2'>
				        	<input type="button" value="Upload" id='upload' hidden='true'  class="btn btn-primary">
				        </div>			        
			        </div>
			    </div>
			    <div class="panel-footer">Click<a href="#" id="singleupload"><b> here </b> </a> to add single member at a time</div>
			    <div class="container col-xs-8 col-xs-offset-4"><label>Please<a href="#" id="downloadExcel"><b> Download </b> </a>valid excel format for uploading</label></div>
		    </div>
		</div>
		
	  </form:form>
</body>
</html>
