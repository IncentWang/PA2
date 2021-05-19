package com.example.PA2;
// No need to convert to JSP
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

@WebServlet(name = "placeOrderServlet", value = "/placeOrderServlet")
public class placeOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root"); // TODO: Change it before deploying to the remote server.
            PreparedStatement statement = con.prepareStatement("INSERT INTO pa2.orders (product_name, orderer_name, total_price) VALUES (?,?,?)");
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
            response.getWriter().print("<head>Place your order successfully!</head>\n" + "<button onclick=\"document.location='./JSPs/store.jsp'\">Continue shop</button>");
        }
    }

    HashMap<String, String> map = new HashMap<String, String>();
    public void init (ServletConfig config)
    {
        map.put ("81611", "Aspen, Colorado");
        map.put ("81411", "Bedrock, Colorado");
        map.put("80908", "Black Forest, Colorado");
        map.put("80301", "Boulder, Colorado");
        map.put("81127", "Chimney Rock, Colorado");
        map.put("80901", "Colorado Springs, Colorado");
        map.put("81223", "Cotopaxi, Colorado");
        map.put("80201", "Denver, Colorado");
        map.put("81657", "Vail, Colorado");
        map.put("80435", "Keystone, Colorado");
        map.put("80536", "Virginia Dale, Colorado");
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String zip = request.getParameter("zip");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (map.containsKey(zip))
                out.write(map.get(zip));
            else
                out.write(" , ");
        }
    }

}
