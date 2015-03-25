
var dataSet;
$(document).ready(function() {
	
    $('#demo').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
 
    $('#example').dataTable( {
        "data": dataSet,
        "columns": [
            { "title": "Id", "class": "center" },
            { "title": "Name" },
            { "title": "Phone No." },
            { "title": "Expiry Date", "class": "center" },
            { "title": "Remarks" }
         ]
    } ); 
} );
