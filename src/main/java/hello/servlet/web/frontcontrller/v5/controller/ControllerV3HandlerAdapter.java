package hello.servlet.web.frontcontrller.v5.controller;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.v3.ControllerV3;
import hello.servlet.web.frontcontrller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap=new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(param-> paramMap.put(param, req.getParameter(param)));
        return paramMap;
    }

}
