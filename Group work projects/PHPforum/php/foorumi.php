<?php
session_start();
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    header("location: login.php");
    exit;
}
require_once "config.php";
?>
<?php
mysqli_report(MYSQLI_REPORT_ALL ^ MYSQLI_REPORT_INDEX);

$json=isset($_POST["postaus"]) ? $_POST["postaus"] : "";

if (!($postaus=tarkistaJson($json))) {
    print "Täytä kaikki kentät";
    exit;
}

try {
    $yhteys=mysqli_connect("localhost", "willhardt21001_2", "lKdrpGnd", "wp_willhardt21001_2");
    }
    catch(Exception $e){
        header("Location:../html/yhteysvirhe.html");
        exit;
    }

$sql="insert into postaus (otsikko, teksti) values(?, ?)";

$stmt=mysqli_prepare($yhteys, $sql);

mysqli_stmt_bind_param($stmt, 'ss', $postaus->otsikko, $postaus->teksti);

mysqli_stmt_execute($stmt);

$tulos=mysqli_query($yhteys, "select * from postaus");

$user="Anonymous";

print "<table>";
print "<tr><th style='color: white; border-bottom: 1px solid white'>Käyttäjä</th>"."<th style='color: white; border-bottom: 1px solid white'>Otsikko</th>"."<th style='color: white; border-bottom: 1px solid white'>Teksti</th>";
while ($rivi=mysqli_fetch_object($tulos)) {
    print "<tr><td style='color: white; padding: 10px; padding-bottom: 30px; border-bottom: 1px solid white'>".$user.
    "<td style='color: white; padding: 10px; padding-bottom: 30px; border-bottom: 1px solid white'>".$postaus->otsikko=$rivi->otsikko.
    "<td style='color: white; padding: 10px; padding-bottom: 30px; border-bottom: 1px solid white'>".$postaus->teksti=$rivi->teksti; 
}
print"</table>";

mysqli_close($yhteys);

?>

<?php
function tarkistaJson($json) {
    if (empty($json)) {
        return false;
    }
    $postaus=json_decode($json, false);
    if (empty($postaus->otsikko) || empty($postaus->teksti)) {
        return false;
    }
    return $postaus;
}

?>