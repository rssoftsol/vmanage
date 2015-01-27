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
	      				<a href="/SpringMVC/members/browsemembers"  ><span id='bm'>My Members</span></a>
	      			</li>
	      
					<li id="addMember"><a href='/SpringMVC/members/addmember'><span id='am' >Add Member</span></a></li>
					<li id="modifyMember"><a href='/SpringMVC/members/modifymember'><span id='mm' >Modify Member</span></a></li>
					<li id="deleteMember"><a href='/SpringMVC/members/deletemember'><span id='dm' >Delete Member</span></a></li>
					
		  		</ul>
			</div>
		</div>
	</nav>
<script type="text/javascript">
	var mode= "${mode}";
	if(mode == "BM")
		$('#myMember').addClass('active');
	else if(mode == "AM")
		$('#addMember').addClass('active');
	else if(mode == "MM")
		$('#modifyMember').addClass('active');
	else if(mode == "DM")
		$('#deleteMember').addClass('active');
</script>
</html>

