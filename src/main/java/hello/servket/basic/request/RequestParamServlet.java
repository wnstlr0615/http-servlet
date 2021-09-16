package hello.servket.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 - start");
        req.getParameterNames()
                .asIterator()
        .forEachRemaining(param-> System.out.println("param = " + param+":"+req.getParameter(param)));
        System.out.println("전체 파라미터 조회 - end");


        System.out.println("단일 파라미터 조회 - start" );
        System.out.println("username : "+req.getParameter("username"));
        System.out.println("단일 파라미터 조회 - end" );

        System.out.println("이름이 같은 파라미터 조회 - start");
        String[] usernames = req.getParameterValues("username");
        for (String username : usernames) {
            System.out.println(username);
        }
        System.out.println("이름이 같은 파라미터 조회 - end");
    }
}
