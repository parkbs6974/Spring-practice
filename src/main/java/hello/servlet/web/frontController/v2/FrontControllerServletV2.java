package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
//왜 * 사용?
//-> v1의 하의에 호출이되면 작동하게 한다

public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();
    //KEY는 URL이 호출되면 ControllerV1을 꺼내서 호출해!


    public FrontControllerServletV2() {
        //"/front-controller/v1/members/new-form"가 호출이 되면, MemberFormControllerV1이 호출이 되게
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();



        //ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        // --> ControllerV1 controllerV1 = new MemberListControllerV1(); 와 같은 것이다

        //만약 front-controller/v1/members/new-form 이 호출이 되면 new MemberFormControllerV1())의 객체 인스턴스가 반환이 되게 한다
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if(controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //여기서 MyView를 사용 하는 부분이다
        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
