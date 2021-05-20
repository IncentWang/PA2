function getCity (zip)
{
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function ()
    {
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            var result = xhr.responseText;
            var place = result.split(',');
            document.getElementById("city").value = place[1];
            document.getElementById("state").value = place[0];
            alert(place[1])
        }
        xhr.open("GET", "getZipcode?zip=" + zip, true);
        xhr.send();
    }
}