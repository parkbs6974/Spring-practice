package hello.servlet.web.frontController.v4.controllerV4;

import hello.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";    //-> return new ModelView("new-form"); V3에서는 이렇게 사용
    }
}
