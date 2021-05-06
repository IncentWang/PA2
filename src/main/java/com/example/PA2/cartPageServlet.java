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
 */
@WebServlet(name = "cartPageServlet", value = "/cartPageServlet")
public class cartPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Product, Integer> products = getCartInfo(session);
        generateCart(products, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        try{
            PrintWriter out = response.getWriter();
            initCart(out);
            float total = 0.0f;
            for(Product key : products.keySet())
            {
                int qty = products.get(key);
                total += key.getPrice();
                out.print("<tr><td>Name: " + key.getName() +" Price: "+ qty*key.getPrice() + "</td></tr>\n");
            }
            out.println("<br>");
            out.println("<br>");
            out.print("<b>Summary: $"+ total + "</b>\n");
            out.print("  </table>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n");
        }catch(Exception e){}

    }

    private void initCart(PrintWriter out){
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Cart</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"left-column\">\n" +
                "  <h2>Order Summary</h2>\n" +
                "  <table>\n");
    }
}
