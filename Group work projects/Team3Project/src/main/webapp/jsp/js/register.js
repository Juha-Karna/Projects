function tarkasta(form) {
    var käyttäjänimi = form.name.value;

    if(käyttäjänimi.length < 4) {
        alert("vähintään 4 merkkinen käyttäjänimi");
        form.username.focus(); 
        return false;
    }
    
    else {
        //alert("kiitos lomakkeen täytöstä")
    }

    var salasana = form.pass.value;
    if(salasana.length < 8)
    {
  alert("Anna vähintään 8 kirjaiminen salasana!");
    }
    else {
    alert("Kiitos!");
    }
}