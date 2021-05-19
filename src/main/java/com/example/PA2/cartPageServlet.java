package com.example.PA2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This Class will be used to generate a cart page.
 * Convert to JSP DONE
 */
@WebServlet(name = "cartPageServlet", value = "/cartPageServlet")
public class cartPageServlet extends HttpServlet {
    float total;
    String productNames = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Product, Integer> products = getCartInfo(session);
        generateCart(products, response);
    }

    private Map<Product, Integer> getCartInfo(HttpSession session){
        ArrayList<String> productNames = getNames(session);
        HashMap<Product, Integer> products = new HashMap<Product, Integer>();
        String sql;
        ResultSet rs;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
            Statement statement = con.createStatement();
            for(String name: productNames){
                sql = "SELECT * FROM pa2.phone_information WHERE phone_name=" + "'" + name + "'";
                rs = statement.executeQuery(sql);
                while(rs.next()){
                    Product product = new Product(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getString(8));
                    products.put(product, products.getOrDefault(product, 0)+1);
                }
            }
        }catch (ClassNotFoundException | SQLException e){

        }
        return products;
    }

    private ArrayList<String> getNames(HttpSession session){
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
    }

    private void generateCart(Map<Product, Integer> products, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        try{

            initCart(out);
            total = 0.0f;
            for(Product key : products.keySet())
            {
                int qty = products.get(key);
                total += key.getPrice();
                productNames += " " + key.getName().replace(' ', '_') + " ";
                out.print("<tr><td>Name: " + key.getName() +" Price: "+ qty*key.getPrice() + "</td></tr>\n");
            }
            out.println("<br>");
            out.println("<br>");
            out.print("<b>Summary: $"+ total + "</b>\n");
            out.print("  </table>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n");
            initForm(out);
        }catch(Exception e){
            out.println("inside catch");
            e.printStackTrace();
        }

    }

    private void initCart(PrintWriter out)
    {
        out.print("<!doctype html>\n" +
                "\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\n" +
                "  <title>Cart Page</title>\n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/store_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/navbar_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/product_description_style.css\">\n" +
                "\n" +
                "  <script type=\"text/javascript\" src=\"js/product_info.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"js/form_validation.js\"></script>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<ul>\n" +
                "<li><a class=\"active\" href=\"./\">Home</a></li>\n" +
                "<li><a href=\"./aboutServlet\">About</a></li>\n" +
                "</ul>\n" +
                "<div class=\"left-column\">\n" +
                "  <h2>Order Summary</h2>\n" +
                "  <table>\n");
    }

    private void initForm(PrintWriter out){
        out.print("<div class=\"right-column\">\n" +
                "        <h2>Order Form</h2>\n" +
                "        <form name=\"orderForm\" action=\"placeOrderServlet\" method=\"post\">\n");
        out.print("<label for=\"fname\">First name:</label>\n" +
                "          <input type=\"text\" id=\"fname\" name=\"fname\" required>\n" +
                "          <br>\n" +
                "          <label for=\"lname\">Last name:</label>\n" +
                "          <input type=\"text\" id=\"lname\" name=\"lname\" required><br><br><br>\n" +
                "\n" +
                "          <label for=\"email\">Email:</label>\n" +
                "          <input type=\"email\" id=\"email\" name=\"email\" required><br><br>\n" +
                "\n" +
                "          <label for=\"address\">Shipping Address:</label>\n" +
                "          <input type=\"text\" id=\"address\" name=\"address\" required><br><br>\n" +
                "    \n" +
                "          <label for=\"city\">City:</label>\n" +
                "          <input type=\"text\" id=\"city\" name=\"city\" style=\"width: 6em\" required>\n" +
                "    \n" +
                "          <label for=\"state\">&nbsp State:</label>\n" +
                "          <input type=\"text\" id=\"state\" name=\"state\" style=\"width: 4em\" required>\n" +
                "    \n" +
                "          <label for=\"zip\">&nbsp Zip:</label>\n" +
                "          <input type=\"text\" id=\"zip\" name=\"zip\" style=\"width: 6em\" pattern=\"[0-9]+\"><br><br>\n" +
                "\n" +
                "          <label for=\"phone\">Phone:</label>\n" +
                "          <input type=\"text\" id=\"phone\" name=\"phone\" pattern=\"(+[0-9])? [0-9]+\"><br><br>\n");
        out.print("<h4>Shipping Method</h4>\n" +
                "          <input type=\"radio\" id=\"overnight\" name=\"shipping\" value=\"overnight\" checked>\n" +
                "          <label for=\"red\">Overnight &nbsp</label>\n" +
                "          <br>\n" +
                "          \n" +
                "          <input type=\"radio\" id=\"expedited\" name=\"shipping\" value=\"expedited\">\n" +
                "          <label for=\"expedited\">2 days expedited &nbsp</label>\n" +
                "          <br>\n" +
                "          <input type=\"radio\" id=\"ground\" name=\"shipping\" value=\"ground\">\n" +
                "          <label for=\"ground\">6 days ground &nbsp</label><br><br>\n" +
                "\n" +
                "\n" +
                "          <label for=\"ccn\">Credit Card Number:</label><br>\n" +
                "          <input type=\"tel\" id=\"ccn\" inputmode=\"numeric\" autocomplete=\"cc-number\" pattern=\"([0-9]{4} ){3}([0-9]{4})|[0-9]{16}\"><br>\n" +
                "\n" +
                "          <label for=\"month\"> Month of Validation Date:</label><br>\n" +
                "          <select id=\"month\" name=\"month\">\n" +
                "            <option value=\"january\">January</option>\n" +
                "            <option value=\"february\">February</option>\n" +
                "            <option value=\"march\">March</option>\n" +
                "            <option value=\"april\">April</option>\n" +
                "            <option value=\"may\">May</option>\n" +
                "            <option value=\"june\">June</option>\n" +
                "            <option value=\"july\">July</option>\n" +
                "            <option value=\"august\">August</option>\n" +
                "            <option value=\"september\">September</option>\n" +
                "            <option value=\"october\">October</option>\n" +
                "            <option value=\"november\">November</option>\n" +
                "            <option value=\"december\">December</option>\n" +
                "          </select><br>\n" +
                "\n" +
                "          <label for=\"year\">Year of Validation Date:</label><br>\n" +
                "          <select id=\"year\" name=\"year\">\n" +
                "            <option value=\"2021\">2021</option>\n" +
                "            <option value=\"2022\">2022</option>\n" +
                "            <option value=\"2023\">2023</option>\n" +
                "            <option value=\"2024\">2024</option>\n" +
                "            <option value=\"2025\">2025</option>\n" +
                "            <option value=\"2026\">2026</option>\n" +
                "            <option value=\"2027\">2027</option>\n" +
                "            <option value=\"2028\">2028</option>\n" +
                "            <option value=\"2029\">2029</option>\n" +
                "          </select><br>\n" +
                "\n" +
                "          <label for=\"cvv\">CVV:</label><br>\n" +
                "          <input type=\"text\" id=\"cvv\" name=\"cvv\" value=\"\" pattern=\"[0-9]{3}\"><br>\n" +
                "          <input type=\"hidden\" id=\"productNames\" value=\"" + productNames.trim() + "\" name=\"productNames\"><br>\n" +
                "          <input type=\"hidden\" id=\"price\" value=\"" + total + "\" name=\"price\"><br>\n" +
                "\n" +
                "        \n" +
                "\n" +
                "          <input type=\"submit\" value=\"Submit\">\n");
        out.print("        </form>\n" +
                "      </div>\n" +
                "    </div>");
    }
}
