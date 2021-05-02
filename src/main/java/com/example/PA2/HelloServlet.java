package com.example.PA2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    Connection connection;

    public void init() {

        // Set database address and the driver will be used
        final String ADDRESS = "localhost:3306"; // TODO: change it before deploy to the server
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        // Set the username and the password of the database
        final String USERNAME = "incent"; // TODO: change it before deploy to the server
        final String PASSWORD = "incent";


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}