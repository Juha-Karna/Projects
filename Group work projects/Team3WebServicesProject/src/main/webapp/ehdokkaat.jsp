<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ehdokas Client</title>
<script src="jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./style.css"/>
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
		    alert("Sorry! Could not update ehdokas!");
		 else
		    alert("Updated Successfully");
		 location.reload();
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
		    alert("Sorry! Could not delete ehdokas!");
		 location.reload();
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
	<div class="header">
	<h1>Ehdokkaat</h1>
	</div>
	<div class="row">	
    <div class="container">
    <div align="center">
	<ul id="ehdokkaat">
	</ul>
	
	<div style="font-size: 1vw;">
	<h3 style="border-top:0.5px solid white;">Update or Delete Ehdokas (Dont delete Ehdokas ID 1)</h3>
	Ehdokas Id
	<br />
	<input type="text" id="id" /> 
	<p />
	<button onclick="deleteEhdokas()">Delete </button>
	<p />
	Ika
	<br />
	<input type="text" id="newika" />
	<p />
	<button onclick="updateIka()">Update ika</button>

	<h3 style="border-top:0.5px solid white;">Add Ehdokas</h3>
	Nimi
	<br />
	<input type="text" id="nimi" />
	<p />
	Ika
	<br />
	<input type="text" id="ika" />
	<p />
	<button onclick="addEhdokas()">Add Ehdokas</button>
	</div>
	
     </div>
       </div>
       </div>
</body>
</html>