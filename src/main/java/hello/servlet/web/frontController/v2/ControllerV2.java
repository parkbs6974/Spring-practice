package hello.servlet.web.frontController.v2;

import hello.servlet.web.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2{

    //Myview를 반환 한다는 점이 중요하다
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
