<%@ page import="com.example.PA2.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 18624
  Date: 2021/5/20
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! Map<Product, Integer> products;
    float total = 0.0f;
    String productNames = "";
%>
<%! private Map<Product, Integer> getCartInfo(HttpSession session) {
    ArrayList<String> productNames = getNames(session);
    HashMap<Product, Integer> products = new HashMap<Product, Integer>();
    String sql;
    ResultSet rs;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
        Statement statement = con.createStatement();
        for (String name : productNames) {
            sql = "SELECT * FROM pa2.phone_information WHERE phone_name=" + "'" + name + "'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getString(8));
                products.put(product, products.getOrDefault(product, 0) + 1);
            }
        }
    } catch (ClassNotFoundException | SQLException e) {

    }
    return products;
}%>
<%! public ArrayList<String> getNames(HttpSession session){
    ArrayList<String> result = new ArrayList<>();
    String[] names = {"Galaxy 5G", "Galaxy Fold", "Galaxy Note", "Galaxy S", "Galaxy Z Flip", "iPhone 10", "iPhone 11", "iPhone 12", "iPhone 7", "iPhone 8"};
    for(String i : names)
    {
        if(session.getAttribute(i) != null)
        {
            for(int x = 0; x < Integer.valueOf(session.getAttribute(i).toString()); x++)
            {
                result.add(i);
            }
        }
    }
    return result;
}%>

<% products = getCartInfo(session);%>
<html>
<head>
    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <title>Cart Page</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="../stylesheets/store_style.css">
      <link rel="stylesheet" href="../stylesheets/navbar_style.css">
      <link rel="stylesheet" href="../stylesheets/product_description_style.css">

      <script type="text/javascript" src="js/product_info.js"></script>
      <script type="text/javascript" src="js/form_validation.js"></script>

    </head>
    <body>
<ul>
    <li><a class="active" href="./store.jsp">Home</a></li>
    <li><a href="./aboutServlet">About</a></li>
    </ul>
<div class="left-column">
      <h2>Order Summary</h2>
      <table>
          <% try{
              for(Product key : products.keySet()){
                  int qty = products.get(key);
                  total += key.getPrice();
                  productNames += " " + key.getName().replace(' ', '_') + " ";%>
          <tr>
              <td>
                  Name: <%= key.getName()%>
                  Price: <%=qty*key.getPrice()%>
              </td>
          </tr>
              <%}%>
          <br>
          <br>
              <b> Summary: $<%=total%> </b>
              <%}catch(Exception e){}%>
      </table>
</div>
<div class="right-column">
    <h2>Order Form</h2>
    <form name="orderForm" action="../placeOrderServlet" method="post">
        <label for="fname">First name:</label>
        <input type="text" id="fname" name="fname" required="">
        <br>
        <label for="lname">Last name:</label>
        <input type="text" id="lname" name="lname" required=""><br><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required=""><br><br>

        <label for="address">Shipping Address:</label>
        <input type="text" id="address" name="address" required=""><br><br>

        <label for="city">City:</label>
        <input type="text" id="city" name="city" style="width: 6em" required="">

        <label for="state">&nbsp; State:</label>
        <input type="text" id="state" name="state" style="width: 4em" required="">

        <label for="zip">&nbsp; Zip:</label>
        <input type="text" id="zip" name="zip" style="width: 6em" pattern="[0-9]+"><br><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" pattern="(+[0-9])? [0-9]+"><br><br>
        <h4>Shipping Method</h4>
        <input type="radio" id="overnight" name="shipping" value="overnight" checked="">
        <label for="red">Overnight &nbsp;</label>
        <br>

        <input type="radio" id="expedited" name="shipping" value="expedited">
        <label for="expedited">2 days expedited &nbsp;</label>
        <br>
        <input type="radio" id="ground" name="shipping" value="ground">
        <label for="ground">6 days ground &nbsp;</label><br><br>


        <label for="ccn">Credit Card Number:</label><br>
        <input type="tel" id="ccn" inputmode="numeric" autocomplete="cc-number" pattern="([0-9]{4} ){3}([0-9]{4})|[0-9]{16}"><br>

        <label for="month"> Month of Validation Date:</label><br>
        <select id="month" name="month">
            <option value="january">January</option>
            <option value="february">February</option>
            <option value="march">March</option>
            <option value="april">April</option>
            <option value="may">May</option>
            <option value="june">June</option>
            <option value="july">July</option>
            <option value="august">August</option>
            <option value="september">September</option>
            <option value="october">October</option>
            <option value="november">November</option>
            <option value="december">December</option>
        </select><br>

        <label for="year">Year of Validation Date:</label><br>
        <select id="year" name="year">
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
            <option value="2024">2024</option>
            <option value="2025">2025</option>
            <option value="2026">2026</option>
            <option value="2027">2027</option>
            <option value="2028">2028</option>
            <option value="2029">2029</option>
        </select><br>

        <label for="cvv">CVV:</label><br>
        <input type="text" id="cvv" name="cvv" value="" pattern="[0-9]{3}"><br>
        <input type="hidden" id="productNames" value="Galaxy_Fold" name="productNames"><br>
        <input type="hidden" id="price" value="799.99" name="price"><br>



        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
