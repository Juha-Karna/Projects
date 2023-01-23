<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Client</title>
<script src="jquery-3.5.1.min.js"></script>
<script>
  $(document).ready( 
	function() {
             $.getJSON("rest/ehdokkaat",{}, showEhdokkaat);
	}
  );

  function showEhdokkaat(ehdokkaat) {
     $.each(ehdokkaat, 
    	 function(idx,Ehd) 
    	 {
    	    $("#ehdokkaat").append("<li>" + Ehd.id + " - " + Ehd.nimi + " - " +  Ehd.ika + "</li>");
         }
     );
  }

  function updateIka() {
    
     $.ajax( 
	    	 {
		       url : "rest/ehdokkaat/" +  $("#id").val(),
      	       data : {"ika": $("#newika").val()},
		       method : "PUT",
		       complete : showUpdateResult
	    	 }
	      ); 
  }

  
  function showUpdateResult(result) {
		 if (result.status == 200)
		    alert("Updated Successfully");
		 else
		    alert("Sorry! Could not update ehdokas!");
  }

  function deleteEhdokas() {
	     $.ajax( 
	    	 {
		       url : "rest/ehdokkaat/" + $("#id").val(),
   	  	       method : "delete",
		       complete : showDeleteResult
	    	 }
	      );
  }

	  
  function showDeleteResult(result) {
		 if (result.status != 404)
		    alert("Deleted Successfully");
		 else
		    alert("Sorry! Could not delete book!");
  }
	
  function addEhdokas() {
	     $.ajax( 
	    	 {
		       url : "rest/ehdokkaat",
      	               data : { "nimi" : $("#nimi").val(), "ika": $("#ika").val()},
		       method : "POST",
		       complete : showAddResult
	    	 }
	      );
  }
    	  
  function showAddResult(result) {
		 alert("Added Successfully");
		 location.reload();
  }
	  
</script>
</head>
<body>
	<h2>Ehdokkaat</h2>
	<ul id="ehdokkaat">
	</ul>

	<h3>Update or Delete Ehdokas</h3>
	Ehdokas Id
	<br />
	<input type="text" id="id" /> 
	<button onclick="deleteEhdokas()">Delete </button>
	<p />
	Ika
	<br />
	<input type="text" id="newika" />
	<p />
	<button onclick="updateIka()">Update ika</button>

	<h3>Add Ehdokas</h3>
	Nimi
	<br />
	<input type="text" id="nimi" />
	<p />
	Ika
	<br />
	<input type="text" id="ika" />
	<p />
	<button onclick="addEhdokas()">Add Ehdokas</button>
</body>
</html>