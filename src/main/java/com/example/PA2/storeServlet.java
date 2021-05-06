package com.example.PA2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Purpose for this class: generate product page (store.html) dynamically.
 * Get info from MySQL database -> generate html
 */
@WebServlet(name = "storeProductServlet", value = "/")
public class storeServlet extends HttpServlet {
    ArrayList<Product> products = new ArrayList<>();
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        products = new ArrayList<>();
        HttpSession session = request.getSession(true);

        int count = 0;

        if (session.isNew()) {
            String userId = String.valueOf(ThreadLocalRandom.current().nextInt());
            session.setAttribute("UserID", userId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/greetingServlet");
            requestDispatcher.include(request, response);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent");
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM pa2.phone_information";
            ResultSet rs = statement.executeQuery(sql);

            initHtml(out);
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
                /* This needs reworking to show up as store page used to appear
                *   @Done */
            }
            String html = generateHtml(products.subList(0, products.size()/2));
            html += generateHtml(products.subList(products.size()/2, products.size()));
            out.print(html);
            out.flush();
            out.print("</body> </html>\n");
        } catch (ClassNotFoundException | SQLException e) {
            out.println("inside catch");
            e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    /**
     *
     * @param ps : The ArrayList which contains the information of all ps.
     * @return : the HTML which will generate the table.
     */
    private String generateHtml(List<Product> ps){
        String html = "<table style=\"width:100%\">\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + "<img class = \"product_image\"" + " onclick= \"document.location='./productServlet?model=" + p.getName() + "'\"" + "src=" + p.getImagePath() + " alt=\"Photo of phone\"" + "width=100px height=auto" +"/>" + "</td>\n";
            // System.out.println(getServletContext().getRealPath("/"));
            // Using this line to find the path of the web app then add "image\"FileName"" behind it to use
        }
        html += "</tr>\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + p.getName() + "</td>\n";
        }
        html += "</tr>\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + p.getBrand() + "</td>\n";
        }
        html += "</tr>\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + p.getColors() + "</td>\n";
        }
        html += "</tr>\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + p.getPrice() + "</td>\n";
        }
        html += "</tr>\n";
        html += "<tr>";
        for (Product p : ps){
            html += "<td>" + "Slot for Rating" + "</td>\n";
        }
        html += "</tr></table>\n";
        return html;
    }

    /**
     * @param out: PrintWriter which used in doGet() to generate HTML.
     *             Generate title and the navigation Bar
     */
    private void initHtml(PrintWriter out)
    {
        out.print("<!doctype html>\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "\n" +
                "  <title>Store</title>\n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/store_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/navbar_style.css\">\n" +
                "  <script type=\"text/javascript\" src=\"js/product_info.js\"></script>\n" +
                "</head>\n");

        out.print("<body>\n" +
                "    \n" +
                "    <ul>\n" +
                "        <li><a href=\"index.html\">Home</a></li>\n" +
                "        <li><a class=\"active\" href=\"store.html\">Store</a></li>\n" +
                "        <li><a href=\"about.html\">About</a></li>\n" +
                "    </ul>\n" +
                "\n" +
                "    <h2 style=\"text-align: center;\">Products</h2>\n");

    }
}
    //    "<ul>\n" +
    //    "<li>Product 1 <a href=\"./reportServlet?id=1\"> Report Product </a></li>\n" +
    //    "<li>Product 2 <a href=\"./reportServlet?id=2\"> Report Product </a></li>\n" +
     //   "<li>Product 3 <a href=\"./reportServlet?id=3\"> Report Product </a></li>\n" +
     //   "<li>Product 4 <a href=\"./reportServlet?id=4\"> Report Product </a></li>\n" +
     //   "<li>Product 5 <a href=\"./reportServlet?id=5\"> Report Product </a></li>\n" +
     //   "</ul>\n"
