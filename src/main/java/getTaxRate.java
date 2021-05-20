// No need to convert to JSP
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;


@WebServlet(name = "getTaxRate", value = "/getTaxRate")
public class getTaxRate extends HttpServlet {

    String file = "D:\\CS\\IN4 124\\PA2\\src\\main\\webapp\\tax_rates2.csv";
    HashMap<String, String> map = new HashMap<String, String>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        try {
            Scanner in = new Scanner(new File(file));
            while (in.hasNextLine())
            {
                String line = in.nextLine();
                String[] words = line.split(",");
                map.put(words[1], words[3]);
                System.out.println(map.size());
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File: " + file + " not found");
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String zip = request.getParameter("zip");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (map.containsKey(zip))
                out.write(map.get(zip));
            else
                out.write("0");
        }
    }

}
