<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@include file="style/style.jsp" %>
	<%@include file="menu.jsp" %>
	<%@include file="submenu.jsp" %>
	<script src="<c:url value="/resources/js/confirmupload.js" />"></script>
	<title>Intimator-Confirm upload</title>
</head>


<script type="text/javascript">
/* dataSet = [['4', 'sharat', '1234', '2015-01-27'], ['3', 'Dillu', '8451046250', '2015-03-28'], 
           ['123', 'Shekhar', '8451046250', '2015-01-30'], ['1', 'rahul', '8451046250', '2015-03-28'], 
           ['12345', 'admin', '8451046250', '2015-01-27'], ['123456', 'test sharma', '8451046250', '2015-03-28'], 
           ['2', 'Subhasis', '8451046250', '2015-01-29'], ['1234', 'Shekhar', '8451046250', '2015-01-28']];
 */
 
 $('#addMember').addClass('active');
var data = "${INVALIDMEMBERS}";
var dataMainArr = [];
var dataSubArr = [];
var dataMainfinalArr = [];

dataMainArr = data.split('!');
for(var i=0;i<dataMainArr.length;i++){
	if(dataMainArr[i]!=''){
	 var dataSubfinalArr = [];
	 dataSubArr = dataMainArr[i].split('~');
	 for(var m=0;m<dataSubArr.length;m++){
	 	dataSubfinalArr.push(dataSubArr[m]);
	 }
	 dataMainfinalArr.push(dataSubfinalArr);
	}
}
dataSet = dataMainfinalArr;
$(document).ready(function() {
	if("${VALIDMEMBERS}" == ''){
	 	$("#confirm").hide();
	}
	$('#cancel').on( 'click', function () {
		  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/cancelupload');
		  	$('form[name=form]').submit();
		});
	
	$('#confirm').on( 'click', function () {
		  	$('form[name=form]').attr('action','${pageContext.request.contextPath}/members/confirmupload');
		  	$('form[name=form]').submit();
	});
});
</script>
<body class="header" >
	<form name="form" method="post">
		<input type="hidden" name="invalidmembers" id="invalidmembers" value="${INVALIDMEMBERS}"/>
		<input type="hidden" name="validmembers" id="validmembers" value="${VALIDMEMBERS}"/>
		
		<div id='demo'></div>
		<div><b><font color="red">${UPLOADINFO}</font></b></div><br>
		<div class="col-xs-12" style="margin-top:5px">
			<div class="col-xs-6">
				<input type="button" id="cancel" value="Cancel" class="btn btn-primary pull-right">
			</div>
			<div class="col-xs-6"><input type="button" id="confirm" value="Confirm" class="btn btn-primary"></div>
		</div>
	</form>
</body>
</html>