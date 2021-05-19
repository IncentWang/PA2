package com.example.PA2;
// Convert to JSP Done
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "aboutServlet", value = "/aboutServlet")
public class aboutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{
        super.init();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print(initHtml());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String initHtml()
    {
        return "<!doctype html>\n" +
                "\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "\n" +
                "  <title>About</title>\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/navbar_style.css\">\n" +
                "  <link rel=\"stylesheet\" href=\"stylesheets/about_style.css\">\n" +
                "  \n" +
                "</head>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<body>\n" +
                "    <ul>\n" +
                "        <li><a href=\"./\">Home</a></li>\n" +
                "        <li><a class=\"active\" href=\"./aboutServlet\">About</a></li>\n" +
                "    </ul>\n" +
                "\n" +
                "    <h2 style=\"text-align: center;\">Our Team</h2>\n" +
                "\n" +
                "    <div class=\"about_us\">\n" +
                "      <p>\n" +
                "        Mobile 124 has opened its doors in April 2021 and is composed of students from UC Irvine.  You can expect \n" +
                "        to be helped by Juan and Weihan with all your cell phone needs!\n" +
                "      </p>\n" +
                "      <p>\n" +
                "        If you have any questions, please feel free to send us an email at questions@mobile124.com. \n" +
                "        We will do our best to respond within 1-3 business days. \n" +
                "      </p>\n" +
                "    </div>\n" +
                "\n" +
                "    <img class=\"uci_img\" src=\"webapp/images/uci.jpg\" alt=\"image of uci\">\n" +
                "   \n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
