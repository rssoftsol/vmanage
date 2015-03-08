
var dataSet;
$(document).ready(function() {
	
    $('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
 
    $('#example').dataTable( {
        "data": dataSet,
        "columns": [
            { "title": "Club Id", "class": "center" },
            { "title": "Club Name" },
            { "title": "Phone No." },
            { "title": "Total members", "class": "center" },
         ]
    } ); 
} );
