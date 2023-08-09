package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String viewPath = "/WEB-INF/views/new-form.jsp";  //WEB-INFO -> 외부에서 그냥 호출 되기를 원하지 않으면 -> CONTROLLER를 거치게 할 수 있게
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//controller에서 view로 이동할떄 사용한다
        dispatcher.forward(request, response);//servlet에서 jsp호출
    }

}
