package hello.servlet.web.frontcontrller.v4;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.MyView;
import hello.servlet.web.frontcontrller.v4.controller.ControllerV4;
import hello.servlet.web.frontcontrller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontrller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontrller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap=new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        String requestURI = req.getRequestURI();
        Optional<ControllerV4>controller
                = Optional.ofNullable(
                        controllerMap.get(requestURI)
        );
        if(controller.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model=new HashMap<>();
        String viewName = controller.get().process(paramMap, model);
        MyView view = viewResolver(viewName);
        view.render(model, req,resp);

    }

    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
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
