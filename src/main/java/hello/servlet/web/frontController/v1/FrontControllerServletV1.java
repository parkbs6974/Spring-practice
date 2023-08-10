package hello.servlet.web.frontController.v1;

import hello.servlet.web.frontController.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontController.v1.controller.MemberListController;
import hello.servlet.web.frontController.v1.controller.MemberSaveController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
//왜 * 사용?
//-> v1의 하의에 호출이되면 작동하게 한다

public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();
    //KEY는 URL이 호출되면 ControllerV1을 꺼내서 호출해!


    public FrontControllerServletV1() {
        //"/front-controller/v1/members/new-form"가 호출이 되면, MemberFormControllerV1이 호출이 되게
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveController());
        controllerV1Map.put("/front-controller/v1/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();



        //ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        // --> ControllerV1 controllerV1 = new MemberListControllerV1(); 와 같은 것이다

        //만약 front-controller/v1/members/new-form 이 호출이 되면 new MemberFormControllerV1())의 객체 인스턴스가 반환이 되게 한다
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        if(controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controllerV1.process(request, response);
    }
}
