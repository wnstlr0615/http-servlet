package hello.servlet.basic.response;

import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet  extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.TEXT_HTML_VALUE);
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>");
        writer.println("hello");
        writer.println("</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("안녕하세요");
        writer.println("</body>");
        writer.println("<html/>");
    }
}
