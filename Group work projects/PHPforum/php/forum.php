<?php
session_start();
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: ../php/login.php");
    exit;
}
require_once "config.php";
?>
<?php
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    echo "<nav class=\"navbar\">
    <ul>
        <nav class=\"navlinks\">
        <li><a href=\"../php/welcome.php\">Home</a></li>
        <li><a href=\"../php/register.php\">Register</a></li>
        <li><a href=\"../php/login.php\">Login</a></li>
      
        </nav>
    </ul>
    </nav>";

} else {
        echo "<nav class=\"navbar\">
        <ul>
        <nav class=\"navlinks\">
        <li><a href=\"../php/welcome.php\">Home</a></li>
        <li><a href=\"../php/forum.php\">Forum</a></li>
        <li><a href=\"../php/logout.php\">Logout</a></li>
        </nav>
    </ul>
    </nav>";
    }

?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Foorumi</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap');
</style>
<script>
    function lahetaPostaus(lomake) {
        var postaus=new Object()
        postaus.otsikko=lomake.otsikko.value;
        postaus.teksti=lomake.teksti.value;
        var jsonPostaus=JSON.stringify(postaus);
        document.getElementById("result").innerHTML="";

        xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("result2").innerHTML = this.responseText;
            }
        };

        xmlhttp.open("POST", "../php/foorumi.php", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("postaus=" + jsonPostaus);
    }
</script>
</head>
<body>
<div class="kontti">
<br>
<form>
<h2>Kirjoita viesti, NYT!</h2>
Otsikko: <input type="text" name="otsikko" placeholder="otsikko"><br>
Teksti: <textarea style="resize: none;" rows="4" cols="30" name="teksti" placeholder="teksti"></textarea><br>
<input type="button" name="ok" value="Lähetä" onclick='lahetaPostaus(this.form);'>
</form>
<p id="result"></p> 
<p id="result2"></p>
</div>
</body>
</html>