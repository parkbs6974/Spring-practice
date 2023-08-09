package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
* 1.파라미터 전송 기능
* http://localhost:8080/request-param?username=hello&age=20
* 
* */

@WebServlet(name = "RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        //request.getParameterNames();//요청 파라미터를 모두 꺼낼 수 있다

        request.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        // paramName -> username, age    request.getParameter -> hello, 20

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파리미터 조회]");
        String age = request.getParameter("age");
        String name = request.getParameter("username");
        System.out.println("age = " + age);
        System.out.println("name = " + name);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]"); //중복으로 보내는 경우는 거의 없다
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }

        response.getWriter().write("ok");
    }
}
