package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();//byte코드로 얻을 수 있다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte를 문자로 바꾸어 줄 때는 항상 어떠한 인코딩 형식인지 알려 주어야 한다

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
