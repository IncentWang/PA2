package com.example.PA2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This Servlet is used to get the parameter from productServlet and put the data into a session attribute to log the things in the cart.
 * NOTE: This servlet will not generate a cart page.
 * No Need to Convert to JSP
 */
@WebServlet(name = "cartServlet", value = "/cartServlet")
public class cartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("name");
        HttpSession session = request.getSession();
        if(session.getAttribute(model) == null) {
            session.setAttribute(model, 1);
        }
        else{
            session.setAttribute(model, Integer.valueOf(session.getAttribute(model).toString()) + 1);
        }
        response.getWriter().print("<head>Successful Add to Cart!</head>\n" + "<button onclick=\"document.location='./JSPs/store.jsp'\">Continue Shopping</button>" + "<button onclick=\"document.location='./JSPs/cartpage.jsp'\">Go To Shopping Cart</button>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
