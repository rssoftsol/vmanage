<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>
<%@ include file="popupinfo.html" %>
<%@ include file="popuperror.html" %>

<title>Intimator-Member Operations</title>
</head>


<script type="text/javascript">
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
        format: "dd/mm/yyyy",
        autoclose: true
    });
    if(!get_mem_button_visibility){
    	$("#getMemBtn").hide();
    }
    if(!text_field_visibility){
    	$( "#name" ).prop( "readonly", true );
    	$( "#phone" ).prop( "readonly", true );
    	$( "#example1" ).prop( "disabled", true );
    	$( "#remarks" ).prop( "disabled", true );
    }
    $('#getMemBtn').on( 'click', function () {
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/view/${mode}/'+document.getElementById('memid').value);
  	  	$('form[name=form]').submit();
  	});
    $('#uploadExcel').on( 'click', function () {
  	  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/member/upload');
  	    $('form[name=form]').attr('method','GET');
  	  	$('form[name=form]').submit();
  	});
    if(mode != 'ADD'){
    	$("#uploadExcel").hide();
    }
    $("#phone").ForceNumericOnly();
} );



</script>
<body class="header" >

	<form:form name="form" action="${pageContext.request.contextPath}/members/memberAction/${mode}" method="post" commandName="commandd">
		<form:input type="hidden" id="id" path="id"/>
		<div class="form-register">
			<div class="panel panel-default">
				<div class="panel-heading header"><h3 class="panel-title">${headermsg}</h3></div>
				<div class="panel-body greybg">
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Id:</label></div>
				        <div class='col-xs-4'><form:input type="text" id='memid' maxlength="20" path="memid" class="form-control"/></div>
				        <div class='col-xs-2'>
				        	<input type="button" value="Get Mem" id='getMemBtn' hidden='true'  class="btn btn-primary">
				        </div>
				        <div class='col-xs-5'><form:errors path="memid" cssClass="alert alert-danger error" element="div"/></div>
				        
			        </div>
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Name:</label></div>
				        <div class='col-xs-4'><form:input type="text" id='name' maxlength="20" path="name" class="form-control"/></div>
				        <div class='col-xs-5'><form:errors path="name" cssClass="alert alert-danger error" element="div"/></div>
			        </div>
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Phone No:</label></div>
				        <div class='col-xs-4'><form:input type="text" id='phone' maxlength="10" path="phone" class="form-control"/></div>
				        <div class='col-xs-5'><form:errors  path="phone" cssClass="alert alert-danger error" element="div"/></div>
			        </div>
			        
			        <div class='col-xs-12'>
				        <div class='col-xs-3'><label>Expiry Date:</label></div>
				        <div class="container col-xs-4">
				            <div class="hero-unit">
				                <form:input placeholder="click to show datepicker"  id="example1" type="text" path="expirydate" class="form-control"/>
				            </div>
				        </div>
				        <div class='col-xs-5 '><form:errors  path="expirydate" cssClass="alert alert-danger error" element="div"/></div>
			       </div>
			       
			       <div class="col-xs-12">
						<div class="col-xs-3"><label>Remarks:</label></div>
						<div class="col-xs-4"><form:textarea id="remarks" maxlength="160" 
						path="remarks" class="form-control"/></div>
					</div>
			        <div><input type="submit" value="${mode}" class="btn btn-primary pull-right"></div>
			    </div>
			    <div class="panel-footer" id="uploadExcel">Click<a href="#"><b> here </b> </a> to upload members in bulk using excel</div>
		    </div>
		</div>
	  </form:form>
</body>
</html>
