<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<nav class="navbar navbar-default navbar-submenu" role="navigation">
  		<div class="container-fluid">
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      		<ul class="nav navbar-nav"> 
	      			<li id="myMember" data-toggle="tooltip" data-placement="bottom" 
	      			title="Search your club members here.By default all members are shown">
	      				<a href="${pageContext.request.contextPath}/members/browsemembers">
	      					<span class='glyphicon glyphicon-search'></span></a>
	      			</li>
	      
					<li id="addMember" data-toggle="tooltip" data-placement="bottom" 
	      			title="Add details of your new club member here">
						<a href='${pageContext.request.contextPath}/members/member/ADD'>
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						</a>
					</li>
					<li id="modifyMember" data-toggle="tooltip" data-placement="bottom" 
	      			title="Update details of your existing club member here">
						<a href='${pageContext.request.contextPath}/members/member/MODIFY'>
							<span class='glyphicon glyphicon-pencil' ></span>
						</a>
					</li>
					<li id="deleteMember" data-toggle="tooltip" data-placement="bottom" 
	      			title="Delete club member here">
						<a href='${pageContext.request.contextPath}/members/member/DELETE'>
							<span class='glyphicon glyphicon-remove' ></span>
						</a>
					</li>
					
					<li id="sms" data-toggle="tooltip" data-placement="bottom" 
	      			title="send sms to club member here">
						<a href='${pageContext.request.contextPath}/members/myOffer.htm'>
							<span>SMS</span>
						</a>
					</li>
					
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
	else if(mode == "SMS")
		$('#sms').addClass('active');
</script>
</html>

