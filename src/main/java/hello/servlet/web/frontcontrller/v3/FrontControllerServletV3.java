package hello.servlet.web.frontcontrller.v3;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.MyView;
import hello.servlet.web.frontcontrller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontrller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontrller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap=new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = req.getRequestURI();
        Optional<ControllerV3>controller
                = Optional.ofNullable(
                        controllerMap.get(requestURI)
        );
        if(controller.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(req);
        ModelView modelView = controller.get().process(paramMap);
        String viewPath = getViewPath(modelView);
        MyView view = viewResolver(viewPath);
        view.render(modelView.getModel(), req,resp);

    }

    private MyView viewResolver(String viewPath) {
        return new MyView(viewPath);
    }

    private String getViewPath(ModelView modelView) {
        return "/WEB-INF/views/" + modelView.getViewName() + ".jsp";
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap=new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(param-> paramMap.put(param, req.getParameter(param)));
        return paramMap;
    }
}
