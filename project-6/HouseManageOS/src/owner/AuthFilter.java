package owner;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import owner.LoginServlet;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();

        if (uri.endsWith("oldUserLogin.html") || uri.endsWith("index.html") ||
                uri.endsWith("png") || uri.endsWith("jpg") ||
                uri.endsWith("css") || uri.endsWith("login") ||
                uri.endsWith("verifyCode") || uri.endsWith("/") ||
                uri.endsWith("newUserEnroll.html")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("./oldUserLogin.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if (toke == Boolean.TRUE) {
                // 登录验证成功
                chain.doFilter(request, response);
            } else {
                // 登录验证失败
                response.sendRedirect("./oldUserLogin.html");
            }
        }
    }
}
