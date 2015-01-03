<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<%@include file="menu.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	$('#confirm').on( 'click', function () {
		var t = $('#example').DataTable();
		var data = t.rows();
		
	});
    var t = $('#example').DataTable();
 
    $('#addRow').on( 'click', function () {
    	t.row.add( [
             		document.getElementById('id').value,
             		document.getElementById('name').value,
             		document.getElementById('phone').value,
             		document.getElementById('example1').value
         		] ).draw();
    	document.getElementById('id').value = '';
 		document.getElementById('name').value = '';
 		document.getElementById('example1').value = '';
 		document.getElementById('phone').value = '';
    	//$.ajax({
    		
    		
            /* url: "${pageContext.request.contextPath}/smartphones/delete/",
            type: "DELETE",
 
              beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
              },
              success: function(smartphone) {
 			  	t.row.add( [
             		document.getElementById('id').value,
             		document.getElementById('name').value,
             		document.getElementById('expDate').value,
             		document.getElementById('phone').value,
         		] ).draw();
                var respContent = "";
                var rowToDelete = $(event.target).closest("tr");
                rowToDelete.remove();
                respContent += "<span class="success">Smartphone was deleted: [";
                respContent += smartphone.producer + " : ";
                respContent += smartphone.model + " : " ;
                respContent += smartphone.price + "]</span>";
                $("#sPhoneFromResponse").html(respContent);        
              }
  */      // });
        //event.preventDefault();
    } );
    
    $('#example1').datepicker({
        format: "dd/mm/yyyy"
    });
    
 
    // Automatically add a first row of data
    //$('#addRow').click();
} );
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members Detail</title>
</head>
<body class="body">
	<form:form action="/intimator/members/addMemberAction" method="post">
	<div>
	  <div>
        <h4 class="modal-title margin-bottom col-xs-12" id="myModalLabel">Provide Member Details</h4>
      </div>
      <div>
        <div class='col-xs-2'><label>Id:</label></div><div class='col-xs-10'><form:input id='memid' path='memid'/></div>
        <div class='col-xs-2'><label>Name:</label></div><div class='col-xs-10'><form:input id='name' path='name'/></div>
        <div class='col-xs-2'><label>Expiry Date:</label></div>
        <div class="container col-xs-10">
            <div class="hero-unit">
                <form:input placeholder="click to show datepicker"  id="example1" path="expirydate"/>
            </div>
        </div>
        <div class='col-xs-2'><label>Phone no.:</label></div><div class='col-xs-10'><form:input type="text" id='phone' path="phone"/></div>
        <div class="col-xs-10 margin-top pull-right">
        	<button type="submit" class="btn btn-primary btn-lg margin-top">
  				add member
	  		</button>
        </div>
      </div>
	  </div>
	  </form:form>
</body>
</html>