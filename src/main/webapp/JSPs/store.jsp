<%@ page import="com.example.PA2.Product" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import ="com.example.PA2.Product"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.concurrent.ThreadLocalRandom" %>
<%--
  Created by IntelliJ IDEA.
  User: IncentWang
  Date: 2021/5/18
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%! ArrayList<Product> products = new ArrayList<>();%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Home Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../stylesheets/store_style.css">
    <link rel="stylesheet" href="../stylesheets/navbar_style.css">
    <link rel="stylesheet" href="../stylesheets/product_description_style.css">

    <script type="text/javascript" src="../js/product_info.js"></script>
    <script type="text/javascript" src="../js/form_validation.js"></script>

</head>
<body>
<ul>
    <li><a class="active" href="store.jsp">Home</a></li>
    <li><a href="about.jsp">About</a></li>
</ul>
<!--
     Because we will get all records from the database, and use them one attribute each time
     So we cannot use sql in jsp directly
-->

<%  try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
    Statement statement = con.createStatement();
    String sql = "SELECT * FROM pa2.phone_information";
    ResultSet rs = statement.executeQuery(sql);


    while (rs.next()) {
        String n = rs.getString("phone_name");
        String nm = rs.getString("phone_brand");
        String nmo = rs.getString("phone_colors");
        float a = rs.getFloat("phone_price");
        int b = rs.getInt("phone_rating");
        int c = rs.getInt("phone_rate_count");
        String phone_d = rs.getString("phone_description");
        String path = rs.getString("image_path");
        products.add(new Product(n,nm,nmo,a,b,c,path));
    }

} catch (ClassNotFoundException | SQLException e) {}
finally {}%>


<table style="width:100%">
    <tr>
            <% for (Product p : products.subList(0, 5)){%>
    <td>
        <img class="product_image"
              onclick=<%="\"document.location='./products.jsp?model=" + p.getName() + "'\"" %>
              src=<%=p.getImagePath()%>
              alt="Photo of phone"
              width=100px
              height=auto/>
    </td>
            <%}%>
    </tr>

    <tr>
            <% for (Product p: products.subList(0, 5)){%>
    <td>
            <%=p.getName()%>
    </td>
            <%}%>
    </tr>

    <tr>
            <% for (Product p: products.subList(0, 5)){%>
    <td>
            <%=p.getBrand()%>>
    </td>
            <%}%>
    </tr>

    <tr>
            <% for (Product p: products.subList(0, 5)){%>
    <td>
            <%=p.getColors()%>>
    </td>
            <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(0, 5)){%>
        <td>
            <%=p.getPrice()%>>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            slot for rating
        </td>
        <%}%>
    </tr>
</table>

<table style="width:100%">
    <tr>
        <% for (Product p : products.subList(5, 10)){%>
        <td>
            <img class="product_image"
                 onclick=<%="\"document.location='./products.jsp?model=" + p.getName() + "'\"" %>
                         src=<%=p.getImagePath()%>
                 alt="Photo of phone"
                 width=100px
                 height=auto/>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            <%=p.getName()%>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            <%=p.getBrand()%>>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            <%=p.getColors()%>>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            <%=p.getPrice()%>>
        </td>
        <%}%>
    </tr>

    <tr>
        <% for (Product p: products.subList(5, 10)){%>
        <td>
            slot for rating
        </td>
        <%}%>
    </tr>
</table>


<jsp:include page="../ratingServlet" />
</body>
</html>
