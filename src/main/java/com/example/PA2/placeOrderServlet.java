package com.example.PA2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "placeOrderServlet", value = "/placeOrderServlet")
public class placeOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "incent"); // TODO: Change it before deploying to the remote server.
            PreparedStatement statement = con.prepareStatement("INSERT INTO orders (product_name, orderer_name, total_price) VALUES (?,?,?)");
            Statement initedStatement = con.createStatement();
            statement.setString(1, request.getParameter("productNames"));
            statement.setString(2, request.getParameter("fname") + request.getParameter("lname"));
            statement.setFloat(3, Float.parseFloat(request.getParameter("price")));
            initedStatement.execute("use pa2");
            statement.execute();


        }catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }finally{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        }
    }
}
