package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v4.controllerV4.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controllerV4.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controllerV4.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
//왜 * 사용?
//-> v4의 하의에 호출이되면 작동하게 한다

public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();
    //KEY는 URL이 호출되면 ControllerV1을 꺼내서 호출해!


    public FrontControllerServletV4() {
        //"/front-controller/v1/members/new-form"가 호출이 되면, MemberFormControllerV1이 호출이 되게
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();



        //ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        // --> ControllerV1 controllerV1 = new MemberListControllerV1(); 와 같은 것이다

        //만약 front-controller/v1/members/new-form 이 호출이 되면 new MemberFormControllerV1())의 객체 인스턴스가 반환이 되게 한다
        ControllerV4 controllerV4 = controllerV4Map.get(requestURI);
        if(controllerV4 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //여기서 paramMap을 이용한다

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();   // 추가가 된다
        String viewName = controllerV4.process(paramMap, model);

        //String viewName = mv.getViewName(); 필요 없어지게 된다

        MyView view = viewResolver(viewName);

        view.render(model,request, response); //--> FrontController가 model을 지원해주기 때문이다
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
