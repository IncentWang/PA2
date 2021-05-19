package com.example.PA2;
// Convert to JSP DONE
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

@WebServlet(name = "productServlet", value = "/productServlet")
public class productServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("model"); // IMPORTANT: This parameter named MODEL

        PrintWriter out = response.getWriter();
        out.print(initHtml());

        Product product;


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM pa2.phone_information WHERE phone_name=" + "'" + name + "'";


            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                product = new Product(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getString(8));
                out.print(generateDetail(product));
            }
        }
        catch(ClassNotFoundException | SQLException e){
            out.println("inside catch");
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String generateDetail(Product product)
    {
        String html = "";
        html += "<h2>Product Description</h2>\n";
        html += "<img class = \"product_image\" src=" + product.getImagePath() + " alt=\"Photo of phone\"/>\n";
        html += "<b>" + product.getName() + "</b>\n";
        html += "<p>Colors: " + product.getColors() + "</p>\n";
        html += "<p>Prices: $" + product.getPrice() + "</p>\n";
        html += "<p>Rating on this model: " + product.getRating() + "</p>\n";
        html += "<button onclick=\"document.location='./cartServlet?name=" + product.getName() + "'\">Add To Cart</button>";
        return html;
    }

    private String initHtml()
    {
        return "<!doctype html>\n" +
                "\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\n" +
                "  <title>Product Page</title>\n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/store_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/navbar_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/product_description_style.css\">\n" +
                "\n" +
                "  <script type=\"text/javascript\" src=\"js/product_info.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"js/form_validation.js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "<ul>\n" +
                "        <li><a class=\"active\" href=\"./\">Home</a></li>\n" +
                "        <li><a href=\"./aboutServlet\">About</a></li>\n" +
                "</ul>\n";
    }
}
