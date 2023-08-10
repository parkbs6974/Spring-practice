package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.Controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.Controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.Controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
//왜 * 사용?
//-> v3의 하의에 호출이되면 작동하게 한다

public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();
    //KEY는 URL이 호출되면 ControllerV1을 꺼내서 호출해!


    public FrontControllerServletV3() {
        //"/front-controller/v1/members/new-form"가 호출이 되면, MemberFormControllerV1이 호출이 되게
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();



        //ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        // --> ControllerV1 controllerV1 = new MemberListControllerV1(); 와 같은 것이다

        //만약 front-controller/v1/members/new-form 이 호출이 되면 new MemberFormControllerV1())의 객체 인스턴스가 반환이 되게 한다
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if(controllerV3 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //여기서 paramMap을 이용한다

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controllerV3.process(paramMap);

        String viewName = mv.getViewName();//논리이름 new-form

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName/*key의 변수명*/ -> paramMap.put(paramName, request.getParameter(paramName)/*value다*/));
        return paramMap;
    }
}
