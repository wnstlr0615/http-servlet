package hello.servlet.web.frontcontrller.v4.controller;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4{
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
