// No need to convert to JSP
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;


@WebServlet(name = "placeOrderServlet", value = "/placeOrderServlet")
public class placeOrderServlet extends HttpServlet {

    String file = "C:\\Users\\JSRamirez\\Desktop\\PA2-master\\src\\main\\webapp\\zip_codes.csv";
    HashMap<String, String> map = new HashMap<String, String>();

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
            response.getWriter().print("<head>Place your order successfully!</head>\n" + "<button onclick=\"document.location='./store.jsp'\">Continue shop</button>");
        }
    }

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                //line is the whole string
                String key = line.substring(0, line.indexOf(",")).replace("\"", "");
                String value = line.substring(line.indexOf(",") + 1).replace("\"", "");
                map.put(key, value);
            }
            reader.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File: " + file + " not found");
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
                out.write(" , ");
        }
    }

}
