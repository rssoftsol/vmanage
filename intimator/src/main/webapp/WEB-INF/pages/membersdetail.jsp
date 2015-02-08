<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>
<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/memberdetails.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script type="text/javascript">
/* dataSet = [['4', 'sharat', '1234', '2015-01-27'], ['3', 'Dillu', '8451046250', '2015-03-28'], 
           ['123', 'Shekhar', '8451046250', '2015-01-30'], ['1', 'rahul', '8451046250', '2015-03-28'], 
           ['12345', 'admin', '8451046250', '2015-01-27'], ['123456', 'test sharma', '8451046250', '2015-03-28'], 
           ['2', 'Subhasis', '8451046250', '2015-01-29'], ['1234', 'Shekhar', '8451046250', '2015-01-28']];
 */
var data = "${dataset}";
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
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members Detail</title>
</head>
<body class="body">
<div id='demo'class="center-start half-width"></div>
</body>
</html>