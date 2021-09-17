package hello.servlet.web.frontcontrller.v3;

import hello.servlet.web.frontcontrller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
