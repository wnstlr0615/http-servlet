package hello.servlet.web.frontcontrller.v2;

import hello.servlet.web.frontcontrller.MyView;
import hello.servlet.web.frontcontrller.v1.ControllerV1;
import hello.servlet.web.frontcontrller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontrller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontrller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontrller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontrller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontrller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap=new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");
        String requestURI = req.getRequestURI();
        Optional<ControllerV2>controller
                = Optional.ofNullable(
                        controllerMap.get(requestURI)
        );
        if(controller.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.get().process(req, resp);
        view.render(req, resp);
    }
}
