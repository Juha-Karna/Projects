<?php 
include "../html/header.html";
?>

<?php
require_once "config.php";
try {
    $yhteys=mysqli_connect("localhost", "willhardt21001_2", "lKdrpGnd", "wp_willhardt21001_2");
    }
    catch(Exception $e){
        header("Location:../html/yhteysvirhe.html");
        exit;
    }

$tulos=mysqli_query($yhteys, "select * from postaus");

print "<table border='1' cellspacing='2' cellpadding='2'>";
while ($rivi=mysqli_fetch_object($tulos)) {
    print "<tr><td>"."<th>".$rivi->username."<td>".$rivi->otsikko."<td>".$rivi->teksti; 
}
print"</table>";

mysqli_close($yhteys);

?>
