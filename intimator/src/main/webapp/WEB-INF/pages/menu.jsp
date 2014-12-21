<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.disable_a_href{
    font: bolder;
}
</style>
<script type="text/javascript">
	function toggleMenuItemsSelection(t){
		
	}
</script>
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav"> 
	      	<li class="active"><a href="/intimator/members/browsemembers"  id="myMembers"><span id='mm'  
	      		class='disable_a_href'>My Members</span></a></li>
	      
			<li><a id="addMembers" href='/intimator/members/addmember'><span id='am' >Add Members</span></a></li>
			<li><a><span class="center-start">${message}</span></a></li>
		  </ul>
	</div>
	</div>
	</nav>
</html>