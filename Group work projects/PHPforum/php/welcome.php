<?php
session_start();
 ?> 
<?php
 include "../php/header.php";
?>
<?php
if(!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true){
    
    echo 
    "
    <hr>
    <br>
    <h1 class=\"unlogged\">Welcome to our site, please log in to access the content.</h1>
    </body>
    </html>";

}else{
    echo
    "
    <hr>
    <br>
    <div class=\"kontti\">
    <h1>Hi, "; echo htmlspecialchars($_SESSION["username"]); echo "<h1> Welcome to our site.</h1>
    <p>
        <a href=\"reset-password.php\" class=\"btn btn-warning\">Reset Your Password</a>
    </p>
</div>

<br>
<h1 class=\"homeh1\"> Homepage </h1>
<br>
            
<div class=\"container\">
    <div class=\"item\">
        <p> TEXT SAMPLE </p>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Integer mattis vestibulum lacus eu viverra.
             Pellentesque quis augue quis orci accumsan accumsan.
              Suspendisse potenti. In venenatis nec tortor vulputate mollis.
               Donec vel interdum tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                Nam sit amet ex eget tortor volutpat volutpat. Quisque laoreet elit metus, a tristique dui auctor eget. Cras quis lacus eget sem convallis gravida.
                 Vestibulum sit amet tellus arcu. Suspendisse fermentum vestibulum urna ut vestibulum. Sed sollicitudin aliquet egestas.
                  Integer imperdiet rutrum consequat. Mauris lobortis dolor ipsum, a euismod ex interdum nec. Aliquam tristique ac mi a varius.
                   Pellentesque lorem nunc, vehicula non nulla at, posuere fringilla justo. </p>
    </div>
    <div class=\"item\">
        <p> TEXT SAMPLE </p>
        <p>Pellentesque efficitur augue feugiat auctor dapibus.
             Sed cursus urna ut ex blandit, tincidunt eleifend sem lacinia.
              Ut sagittis vulputate diam, vel tempus dolor mollis sit amet.
               Curabitur id lorem ex.
                Pellentesque fermentum elit sit amet dolor congue accumsan.
                 Quisque nec metus eget orci mattis posuere.
                  Integer interdum volutpat magna at feugiat.
                   Aenean facilisis blandit sem, at porttitor diam aliquam eget.
                    Aenean tempus odio id turpis feugiat, et cursus dui tempus.
                     Vestibulum nec laoreet sapien, et laoreet sem.
                      Vestibulum sit amet lacus vitae nibh vestibulum iaculis eu in urna.
                       Cras posuere, quam ac mattis efficitur, tellus urna imperdiet risus, id iaculis elit eros ut erat.
                        Fusce sit amet placerat velit. Pellentesque ac imperdiet orci.
                         Praesent suscipit accumsan magna a consequat.</p>
    </div>
</div>
";   
}
?>

