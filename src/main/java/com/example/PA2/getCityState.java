package com.example.PA2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import java.util.HashMap;

@WebServlet(name = "getCityState", value = "/getCityState")
public class getCityState extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
