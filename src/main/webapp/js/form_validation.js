function validateForm(){
    var color = document.forms['order_form']['color'].value;
    var quantity = document.forms['order_form']['quantity'].value;
    var fName = document.forms['order_form']['fname'].value;
    var lName = document.forms['order_form']['lname'].value;
    var address = document.forms['order_form']['address'].value;
    var city = document.forms['order_form']['city'].value;
    var state = document.forms['order_form']['state'].value;
    var zip = document.forms['order_form']['zip'].value;
    var phone = document.forms['order_form']['phone'].value;
    var card = document.forms['order_form']['card'].value;
    var shipping = document.forms['order_form']['shipping'].value;


    if (color === ""){
        alert("Color must be selected");
        return false;
    }

    if  (quantity < 0){
        alert("Quantity must be greater than 0");
        return false;
    }
    
    if (fName === ""){
        alert("First name cannot be blank");
        return false;
    }
    
    if (lName === ""){
        alert("Last name cannot be blank");
        return false;
    }

    if (address === ""){
        alert("Address cannot be blank");
        return false;
    }

    if (city === ""){
        alert("City cannot be blank");
        return false;
    }

    if (state === ""){
        alert("State cannot be blank");
        return false;
    }

    if (zip === ""){
        alert("Zip Code cannot be blank");
        return false;
    }

    if (phone === ""){
        alert("Phone Number is invalid");
        return false;
    }
   

    if (card === ""){
        alert("Card Info cannot be blank");
        return false;
    }

    if (shipping === ""){
        alert("Shipping Method cannot be blank");
        return false;
    }

    
    
    sendEmail(color, quantity, fName, lName, address, city, state, zip, phone, card, shipping);
    
    return true;

}



function sendEmail(color, quantity, fName, lName, address, city, state, zip, phone, card, shipping){
    console.log("send email");
    var body = "Color: " + color + '\r\n' + 
                "Quantity: " + quantity + '\r\n' + 
                "First Name: " + fName + '\r\n' + 
                "Last Name: " + lName + '\r\n' + 
                "Address: " + address + '\r\n' + 
                "City: " + city + '\r\n' + 
                "State: " + state + '\r\n' + 
                "Zip: " + zip + '\r\n' + 
                "Phone: " + phone + '\r\n' +
                "Card: " + card + '\r\n' + 
                "Shipping: " + shipping;

    body = encodeURIComponent(body);

    var subject = "mobile device order form";

    window.open('mailto:test@example.com?subject=' + subject + '&body=' + body);
}