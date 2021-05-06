package com.example.PA2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ratingServlet", value = "/ratingServlet")
public class ratingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<String> products = new ArrayList<>();
        ArrayList<Product> ps = new ArrayList<>();

        int counter = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM pa2.orders";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                getName(products, rs.getString(2));
            }

            for(int i = products.size() - 1; i >=0; i--)
            {
                Statement statement1 = con.createStatement();
                String sql1 = "SELECT * FROM pa2.phone_information where phone_name=" + "'" + products.get(i) + "'";
                System.out.println(sql1);
                ResultSet rs1 = statement1.executeQuery(sql1);
                while(rs1.next()){
                    ps.add(new Product(rs1.getString("phone_name"), rs1.getString("phone_brand"),
                            rs1.getString("phone_colors"), rs1.getFloat("phone_price"),
                            rs1.getInt("phone_rating"), rs1.getInt("phone_rate_count"),
                            rs1.getString("image_path")));
                }
                if(ps.size() == 5){
                    break;
                }
            }
            response.getWriter().print(generateHtml(ps) + "\n");

        }
        catch(ClassNotFoundException | SQLException e){
            out.println("inside catch");
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    private void getName(ArrayList<String> a, String s) {
        String[] strings = s.split(" ");
        for(String i : strings)
        {
            a.add(i.replace('_', ' '));
        }
        System.out.println(a.toString());
    }

    private String generateHtml(ArrayList<Product> ps)
    {
        String html = "<table style=\"width:100%\">\n";
        html += "<tr>";
        int i = 1;
        for (Product p : ps){
            html += "<td>" + "<img class = \"product_image\"" + " onclick= \"document.location='./productServlet?model=" + p.getName() + "'\"" + "src=" + p.getImagePath() + " alt=\"Photo of phone\"" + "width=100px height=auto" +"/>" + "</td>\n";
        }
        html += "</tr><tr>";
        for (Product p : ps){
            html += "<td><div class=\"rate\">\n" +
                    "    <input type=\"radio\" id=\"star5\" name=\"rate" + i + "\" value=\"5\" />\n" +
                    "    <label for=\"star5\" title=\"text\">5 stars</label>\n" +
                    "    <input type=\"radio\" id=\"star4\" name=\"rate" + i + "\" value=\"4\" />\n" +
                    "    <label for=\"star4\" title=\"text\">4 stars</label>\n" +
                    "    <input type=\"radio\" id=\"star3\" name=\"rate" + i + "\" value=\"3\" />\n" +
                    "    <label for=\"star3\" title=\"text\">3 stars</label>\n" +
                    "    <input type=\"radio\" id=\"star2\" name=\"rate" + i + "\" value=\"2\" />\n" +
                    "    <label for=\"star2\" title=\"text\">2 stars</label>\n" +
                    "    <input type=\"radio\" id=\"star1\" name=\"rate" + i + "\" value=\"1\" />\n" +
                    "    <label for=\"star1\" title=\"text\">1 star</label>\n" +
                    "  </div></td>";
            i++;
        }
        html += "</tr></table>\n";
        return html;
    }
}
