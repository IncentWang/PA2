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
    final String DATABASE_USERNAME = "root"; // TODO: Change it before deployment
    final String DATABASE_PASSWORD = "root"; // TODO: Change it before deployment
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
        out.print(initHtml());


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
                "<body>" +
                "<ul>\n" +
                "        <li><a class=\"active\" href=\"./\">Home</a></li>\n" +
                "        <li><a href=\"./aboutServlet\">About</a></li>\n" +
                "</ul>\n";
    }
}
