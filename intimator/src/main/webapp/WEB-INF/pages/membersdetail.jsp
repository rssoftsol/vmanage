<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="menu.jsp" %>
<html>
<link href="<c:url value="/resources/css/jquery.dataTables.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>

<script src="<c:url value="/resources/js/table.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script type="text/javascript">
dataSet = [
               ['1','Shekhar','8451046250','16 Dec 14'],
               ['2','Subhasis','9821181970','01 Jan 2015'],
               ['3','Dillu','9768914599' ,'25 Mar 2015'],
               ['4','Sharat','9320020888','16 Jan 2015'],
               ['5','Bharat','8433172249','31 Dec 2014'],
               ['6','Priya','9821181977','4 April 2014']
           ];
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members Detail</title>
</head>
<body class="body">
<div id='demo'class="center-start half-width"></div>
</body>
</html>