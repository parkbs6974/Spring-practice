package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        //response.setStatus(HttpServletResponse.SC_OK); //Http 응답 코드를 넣을 수 있다, 기본이 200이다, HttpServletResponse.SC_OK 이게 200보다 낫다
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-revalidate"); //cache를 무효화 하겠다
        response.setHeader("Pragma", "no-cache"); //과거 버전까지 cache 무효화
        response.setHeader("my-header", "hello"); //내가 header에 임의로 만들 수가 있다

        //[header의 편의 method]
        //(response);
        //cookie(response);
        //redirect(response);



        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html"); --> 위에꺼가 아니라도 302가 나온다
        response.sendRedirect("/basic/hello-form.html");
    }

}
