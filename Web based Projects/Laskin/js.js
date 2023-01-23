var merkit = ['+','-','*','/','%'];
var num = '';
var luku = '';
var index = 0;
function nappi(luku)
{
  num += luku;
  document.getElementById('näyttö').innerHTML = num;
}

function laske()
{
  var jono;
  if(num.search("%") > 0)
  {
    jono = num.split('%');
    num = jono[0] * jono[1] / 100;
    document.getElementById('näyttö').innerHTML = num;
  }
  else{
    document.getElementById('näyttö').innerHTML = eval(num);
    num = eval(num);
  }
}
function reset()
{
  num = '';
  document.getElementById('näyttö').innerHTML = num;
}

function pyyhi()
{
  for(var i = 0; i < num.length; i++)
  {
    for(var j = 0; j < merkit.length; j++)
    {
      if(num[i] == merkit[j])
      {
        index = i;
      }
    }
  }
  num = num.substr(0, index);
  document.getElementById('näyttö').innerHTML = num;
}