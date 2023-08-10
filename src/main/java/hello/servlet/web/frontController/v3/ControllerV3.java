package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
    //(Map<String, String> paramMap): 이 메서드는 paramMap이라는 이름의 매개변수를 받습니다. 이 매개변수는 Map 형태이며, 키와 값이 모두 문자열 형태로 저장되어 있습니다. 이것은 아마도 컨트롤러가 요청 매개변수를 받아 처리하기 위한 정보를 담고 있는 것으로 보입니다.


}
