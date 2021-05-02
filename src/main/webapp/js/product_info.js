
function create(model, brand, price, rating){
    window.location = "product_description.html?model=" + model + "&brand=" + brand + "&price=" + price + "&rating=" + rating;
}

function getInput(formName){
    var fname = document.forms[formName]["fname"];
    var lname = document.forms[formName]["lname"];
    var quanity = document.forms[formName]["quantity"];
    var address = document.forms[formName]["address"];
    var city = document.forms[formName]["city"];
    var state = document.forms[formName]["state"];
    var zip = document.forms[formName]["zip"];
    var phone = document.forms[formName]["phone"];
    var color = document.forms[formName]["color"];
    var shippingMethod = document.forms[formName]["shipping"];
    var email = document.forms[formName]["email"];
    var model = document.getElementById("model").textContent;
    var price = document.getElementById("price").textContent.substr(2);


    if (!(fname.checkValidity() && lname.checkValidity() && quanity.checkValidity() && address.checkValidity() && city.checkValidity() && state.checkValidity() && zip.checkValidity() && phone.checkValidity() && email.checkValidity()))
    {
        return;
    }
    var l = "mailto:" + email.value + "?&subject=Your%20Order%20Details" +
        "&body=First%20Name:%20" + fname.value  + "%0d%0a" +
        "Last%20Name:%20" + lname.value + "%0d%0a" +
        "Model:%20" + model + "%0d%0a" +
        "Color:%20" + color.value + "%0d%0a" +
        "Price:%20" + price + "%0d%0a" +
        "Quanity:%20" + quanity.value+ "%0d%0a"+
        "Shipping%20Address:%20" + address.value + "%20" + city.value + "%20" + state.value + "%20" + zip.value + "%0d%0a" +
        "Shipping%20Method:%20" + shippingMethod.value + "%0d%0a" +
        "Contact%20phone:%20" + phone.value;
    window.location = l;
}

function loadProduct(){

    var urlParams = new URLSearchParams(window.location.search);
    var model = urlParams.get('model');
    var brand = urlParams.get('brand');
    var price = urlParams.get('price');
    var rating = urlParams.get('rating');
   
    var img = "images/" + getImageURL(model);
    
    var img_tag = document.createElement("img");
    img_tag.setAttribute('src', img);
    img_tag.setAttribute('width', '60%');
    img_tag.setAttribute('height', 'auto');
    var img_element = document.getElementById("image");
    img_element.appendChild(img_tag);

    var model_tag = document.createElement("h1");
    var model_node = document.createTextNode(model);
    model_tag.appendChild(model_node);
    var model_element = document.getElementById("model");
    model_element.appendChild(model_tag);

    var brand_tag = document.createElement("h2");
    var brand_node = document.createTextNode(brand);
    brand_tag.appendChild(brand_node);
    var brand_element = document.getElementById("brand");
    brand_element.appendChild(brand_tag);

    var price_tag = document.createElement("h2");
    var price_node = document.createTextNode("$ " + price);
    price_tag.appendChild(price_node);
    var price_element = document.getElementById("price");
    price_element.appendChild(price_tag);


    var numOfStars = "";
    var i = 0;
    for (i; i < rating; i++) {
        numOfStars = numOfStars + "<span class=\"fa fa-star checked\"></span>";
    }

    if (rating < 5){
        for (i; i < 5; i++){
            numOfStars = numOfStars + "<span class=\"fa fa-star\"></span>";
        }
        
    }
    var rating_element = document.getElementById("rating");
    rating_element.innerHTML=numOfStars;
}

function getImageURL(model){
    var url;

    switch (model) {
        case 'iphone 12':
            url = "iphone 12.jpg";
            break;
        case "iphone 11":
            url = "iphone 11.jpg";
            break;
        case "iphone 10":
            url = "iphone 10.jpg";
            break;
        case "iphone 8":
            url = "iphone 8.jpg";
            break;
        case "iphone 7":
            url = "iphone 7.jpg";
            break;
        case "Galaxy 5G":
            url = "Samsung Galaxy 5G.jpg";
            break;
        case "Galaxy Fold":
            url = "Samsung Galaxy Fold.jpg";
            break;
        case "Galaxy Note":
            url = "Samsung Galaxy Note.jpg";
            break;
        case "Galaxy S":
            url = "Samsung Galaxy S.jpg";
            break;
        case "Galaxy Z Flip":
            url = "Samsung Galaxy Z Flip.jpg";
            break;

        default:
            url = null;
    }

    return url;
}