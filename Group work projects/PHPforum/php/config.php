<?php
/* Database credentials */
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'willhardt21001_2');
define('DB_PASSWORD', 'lKdrpGnd');
define('DB_NAME', 'wp_willhardt21001_2');
 

$link = mysqli_connect(DB_SERVER , DB_USERNAME, DB_PASSWORD, DB_NAME);
 
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}


?>