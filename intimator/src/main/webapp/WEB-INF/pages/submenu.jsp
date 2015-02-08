<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<nav class="navbar navbar-default subheader" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
	      			<li id="myMember">
	      				<a href="${pageContext.request.contextPath}/members/browsemembers"  ><span id='bm'><b>My Members</b></span></a>
	      			</li>
	      
					<li id="addMember"><a href='${pageContext.request.contextPath}/members/member/ADD'><span id='am' ><b>Add Member</b></span></a></li>
					<li id="modifyMember"><a href='${pageContext.request.contextPath}/members/member/MODIFY'><span id='mm' ><b>Modify Member</b></span></a></li>
					<li id="deleteMember"><a href='${pageContext.request.contextPath}/members/member/DELETE'><span id='dm' ><b>Delete Member</b></span></a></li>
					
		  		</ul>
			</div>
		</div>
	</nav>
<script type="text/javascript">
	var mode= "${mode}";
	if(mode == "BROWSE")
		$('#myMember').addClass('active');
	else if(mode == "ADD")
		$('#addMember').addClass('active');
	else if(mode == "MODIFY")
		$('#modifyMember').addClass('active');
	else if(mode == "DELETE")
		$('#deleteMember').addClass('active');
</script>
</html>

