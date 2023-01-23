function myFunction() {
    var x = document.getElementById("menu-items");
    if (x.style.display === "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }
  function changeclass() {
    var NAME = document.getElementById("showhide")
    if (NAME.className==="fa fa-bars"){
      NAME.className="fa fa-times";
    }
    else {
      NAME.className=("fa fa-bars")
    }
  }