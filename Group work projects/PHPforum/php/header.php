<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="../css/style.css"> 
    <style> @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap'); </style>
</head>
<body>
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

<!-- <nav class="navbar">
<ul>
    <nav class="navlinks">
    <li><a href="../php/welcome.php">Home</a></li>
    <li><a href="../php/register.php">Register</a></li>   
    <li><a href="../php/login.php">Login</a></li>
    <li><a href="../php/forum.php">Forum</a></li>
    </nav>
</ul>
</nav>
<hr> -->
