package com.example.PA2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
// No Need to convert to JSP
@WebServlet(name = "greetingServlet", value = "/greetingServlet")
public class greetingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      // response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String userId = (String) session.getAttribute("UserID");

       // out.println("<h1> Hello " + userId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
