<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>
<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/register.css" />" rel="stylesheet">


<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>


<script type="text/javascript">
$(document).ready(function() {
	
    //$('#addRow').on( 'click', function () {
    	
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
    //} );
    
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
	<form action="/SpringMVC/members/memberAction/MM" method="post">
		<div class="form-register">
			<div class="panel panel-default">
			<div class="panel-body greybg">
		        <div class="panel-heading header"><h3 class="panel-title">Modify Member Details</h3></div><br>
		        <div class='col-xs-12'><label>${result}</label></div>
		        <div class='col-xs-3'><label>Id:</label></div>
		        <div class='col-xs-9'><input type="text" id='memid' name="memid"/>
		        <input type="button" value="Get Member" class="btn btn-primary"></div>
		        <div class='col-xs-3'><label>Name:</label></div>
		        <div class='col-xs-9'><input type="text" id='name' name="name"/></div>
		        
		        <div class='col-xs-3'><label>Phone No:</label></div>
		        <div class='col-xs-9'><input type="text" id='phone' name="phone"/></div>
		        <div class='col-xs-3'><label>Expiry Date:</label></div>
		        <div class="container col-xs-9">
		            <div class="hero-unit">
		                <input placeholder="click to show datepicker"  id="example1" type="text" name="expirydate"/>
		            </div>
		        </div>
		        <div><input type="submit" value="Modify" class="btn btn-primary pull-right"></div>
		    </div>
		    </div>
		</div>
	  </form>
</body>
</html>