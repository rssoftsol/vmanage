jQuery(document).ready(function($) {

	$('#msg').html("This is updated by jQuery")

});

function formatdate(date){
	var dt = date.split('-');
	return dt[2]+'/'+dt[1]+'/'+dt[0];
}

function test(){
	
}