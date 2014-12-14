<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
<%@include file="menu.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
    var t = $('#example').DataTable();
 
    $('#addRow').on( 'click', function () {
    	t.row.add( [
             		document.getElementById('id').value,
             		document.getElementById('name').value,
             		document.getElementById('example1').value,
             		document.getElementById('phone').value,
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
<body>
<div>${message}</div>
<!-- <input type="button" id='addRow' value='add members'/>
 -->
 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  add members
</button><br>
<div id='demo'></div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Provide Member Details</h4>
      </div>
      <div class="modal-body">
        <div class='span2'>Id:</div><div class='span4'><input type="text" id='id' name='id'></div>
        <div><div class='span2'>Name:</div><div class='span4'><input type="text" id='name' name='name'></div></div>
        <div><div class='span2'>Expiry Date:</div><!-- <div class='span4'><input type="text" id='expDate' name='expDate'></div> -->
        
        <div class="container">
            <div class="hero-unit">
                <input  type="text" placeholder="click to show datepicker"  id="example1">
            </div>
        </div>
        </div>
        
        
        <div><div class='span2'>Phone no.:</div><div class='span4'><input type="text" id='phone' name='phone'></div></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id='addRow'>Save changes</button>
      </div>
    </div>
  </div>
</div>
 
 
</body>
</html>