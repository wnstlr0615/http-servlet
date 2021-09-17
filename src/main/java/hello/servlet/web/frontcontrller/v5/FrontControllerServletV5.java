package hello.servlet.web.frontcontrller.v5;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.MyView;
import hello.servlet.web.frontcontrller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontrller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontrller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontrller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontrller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontrller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontrller.v5.controller.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontrller.v5.controller.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap=new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        intitHandlerAdapters();
    }

    private void intitHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);
        if(handler==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView modelView = adapter.handle(req, resp, handler);

        MyView view = viewResolver(modelView.getViewName());
        view.render(modelView.getModel(), req,resp);
    }
    private MyView viewResolver(String viewPath) {
        return new MyView("/WEB-INF/views/" + viewPath + ".jsp");
    }
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler ="+handler);
    }

    private Object getHandler(HttpServletRequest req) {
        return handlerMappingMap.get(req.getRequestURI());
    }
}
