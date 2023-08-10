package hello.servlet.web.frontController.v2.controller;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*---> 기존의 코드(MyView를 사용하기 이전)
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);*/

        /*MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        return myView;   -> Inline 방식을 사용하면 더 깔끔해 진다*/
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
