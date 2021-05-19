<%@ page import="com.example.PA2.Product" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: 18624
  Date: 2021/5/19
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! Product product;%>
<html>
<head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <title>Product Page</title>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="../stylesheets/store_style.css">
      <link rel="stylesheet" href="../stylesheets/navbar_style.css">
      <link rel="stylesheet" href="../stylesheets/product_description_style.css">

      <script type="text/javascript" src="js/product_info.js"></script>
      <script type="text/javascript" src="js/form_validation.js"></script>

</head>
<body>
<ul>
    <li><a class="active" href="store.jsp">Home</a></li>
    <li><a href="about.jsp">About</a></li>
</ul>

<%
      try
      {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM pa2.phone_information WHERE phone_name=" + "'" + request.getParameter("model") + "'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                  product = new Product(rs.getString(1), rs.getString(2),
                          rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getString(8));
            }
      } catch (ClassNotFoundException | SQLException e) {}
      finally {}
%>

<h2>
      Product Description
</h2>

<img class="product_image" src="<%=product.getImagePath()%>" alt="Photo of phone">
<b>
      <%=product.getName()%>
</b>
<p>
      Colors: <%=product.getColors()%>
</p>
<p>
      Prices: <%=product.getPrice()%>
</p>
<p>
      Rating on this model: <%=product.getRating()%>
</p>

<button onclick="document.location='../cartServlet?name=<%=product.getName()%>'">
      Add to Cart
</button>
</body>
</html>