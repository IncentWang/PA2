function getPlace (zip)
{
  if (window.XMLHttpRequest)
  {  // IE7+, Firefox, Chrome, Opera, Safari
     var xhr = new XMLHttpRequest();
  }
  else
  {  // IE5, IE6
     var xhr = new ActiveXObject ("Microsoft.XMLHTTP");
  }

  xhr.onreadystatechange = function ()
  { // 4 means finished, and 200 means okay.
    if (xhr.readyState == 4 && xhr.status == 200)
    { // Data should look like "Fairfax, Virginia"
      var result = xhr.responseText;
      var place = result.split (',');
      //if (document.getElementById ("city").value == "")
        document.getElementById ("city").value = place[0];
      //if (document.getElementById ("state").value == "")
        document.getElementById ("state").value = place[1];
    } 
  } 
  // Call the response software component
  xhr.open ("GET", "getCityAndState?zip=" + zip, true);
  xhr.send ();
/*  console.log("hit");
  xhr.open ("POST", "./getCityAndState", true);
  console.log("hit");
  xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
  xhr.send ("zip="+zip);  */
}

function getTaxRate (zip)
{
  if (window.XMLHttpRequest)
  {  // IE7+, Firefox, Chrome, Opera, Safari
    var xhr = new XMLHttpRequest();
  }
  else
  {  // IE5, IE6
    var xhr = new ActiveXObject ("Microsoft.XMLHTTP");
  }

  xhr.onreadystatechange = function ()
  { // 4 means finished, and 200 means okay.
    if (xhr.readyState == 4 && xhr.status == 200)
    { // Data should look like "Fairfax, Virginia"
      var result = xhr.responseText;
      //if (document.getElementById ("city").value == "")
      var total = document.getElementById("summary").value;
      var tax = total * result;
      document.getElementById("displaySummary").innerText = "Summary: $" + parseFloat(parseFloat(tax) + parseFloat(total));
      //if (document.getElementById ("state").value == "")
    }
  }
  // Call the response software component
  xhr.open ("GET", "getTaxRate?zip=" + zip, true);
  xhr.send ();
  /*  console.log("hit");
    xhr.open ("POST", "./getCityAndState", true);
    console.log("hit");
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send ("zip="+zip);  */
}
