package hello.servlet.web.frontcontrller.v5.controller;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.v4.controller.ControllerV4;
import hello.servlet.web.frontcontrller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model=new HashMap<>();

        String viewPath = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewPath);
        modelView.setModel(model);
        return modelView;
    }
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap=new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(param-> paramMap.put(param, req.getParameter(param)));
        return paramMap;
    }
}
