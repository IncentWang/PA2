package com.example.PA2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/getZipcode"})
public class getZipcode extends HttpServlet {
    HashMap<String, String> zipcodes = new HashMap<>();
    String line;
    public void init (ServletConfig servletConfig){
        try {
            Scanner in = new Scanner(new File("D:\\CS\\IN4 124\\PA2\\src\\main\\webapp\\zip_codes.csv"));
            while (in.hasNext())
            {
                line = in.nextLine();
                if (line != null)
                {
                    String[] zipcode = line.split(",", 1);
                    zipcodes.put(zipcode[0], zipcode[1]);
                }
            }
        }catch (Exception e)
        {}
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println("hit! ");
        String zip = request.getParameter("zip");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            if (zipcodes.containsKey(zip))
            {
                out.write(zipcodes.get(zip));
            }
            else
            {
                out.write(" , ");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
