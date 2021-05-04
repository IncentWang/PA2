package com.example.PA2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "reportServlet", value = "/reportServlet")
public class reportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String productID = request.getParameter("id");
    FileWriter fileWriter = new FileWriter(getServletContext().getRealPath("/reported_products.txt"), true);
    HttpSession session = request.getSession(true);
    String userID = (String) session.getAttribute("UserID");
    fileWriter.write("Product " + productID + " has been reported by " + userID +"\n");
    fileWriter.close();

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
    requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
