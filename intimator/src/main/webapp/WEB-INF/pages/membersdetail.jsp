
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="style/style.jsp" %>
<script src="<c:url value="/resources/js/memberdetails.js" />"></script>
<%@include file="menu.jsp" %>
<%@include file="submenu.jsp" %>

<title>Intimator-Members Detail</title>
</head>

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
<body class="header" >
<div id='demo'class="center-start half-width"></div>
</body>
</html>