package hello.servlet.web.frontcontrller.v3.controller;

import hello.servlet.web.frontcontrller.ModelView;
import hello.servlet.web.frontcontrller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
