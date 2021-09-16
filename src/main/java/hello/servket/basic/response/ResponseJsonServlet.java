package hello.servket.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servket.basic.HelloData;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper mapper=new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //resp.setCharacterEncoding("utf-8");

        resp.getWriter().println(
                mapper.writeValueAsString(
                        new HelloData("joon",20)
                )
        );
    }
}
