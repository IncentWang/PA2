package com.example.PA2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

/**
 * Purpose for this class: generate product page (store.html) dynamically.
 * Get info from MySQL database -> generate html
 */
@WebServlet(name = "generateProduct")
public class generateProductServlet extends HttpServlet{

    final String DATABASE_ADDRESS = "localhost:3306"; // TODO: Change it before deployment
    final String DATABASE_USERNAME = "incent"; // TODO: Change it before deployment
    final String DATABASE_PASSWORD = "incent"; // TODO: Change it before deployment
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    public void init() {}

    /**
     *
     * @param request: ignore for now
     * @param response: ignore for now
     * @throws IOException: ignore for now
     * out: write html to that with out.println() or out.print()
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        initHtml(out);
        try(Connection connection = DriverManager.getConnection(JDBC_DRIVER, DATABASE_USERNAME, DATABASE_PASSWORD)){
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM pa2.phone_information")){ // TODO: Change the name of table before deployment
                    getInfo(resultSet, out);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong during operating the database");
        }
    }

    private void getInfo(ResultSet rs, PrintWriter out){
        try{
            while(rs.next()){
            // TODO: get info from the result set then write it to out to generate HTML
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong during operating the database");
        }
    }

    /**
     * @param out: PrintWriter which used in doGet() to generate HTML.
     * Generate title and the navigation Bar
     */
    private void initHtml(PrintWriter out){
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
