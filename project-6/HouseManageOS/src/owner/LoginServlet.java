package owner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public final static String LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        System.out.println("name " + name + " user "+ user +  " password " + password);

        // 新用户注册
        if (name != null) {
            String verifyCode = (String) request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_VERIFY_CODE);
            if (code == null || !code.equals(verifyCode)) {
                response.sendRedirect("./newUserEnroll.html");
                return;
            }
            HttpSession session = request.getSession(false);
            session.setAttribute(LOGIN_TOKEN, Boolean.TRUE);
            Owner owner = new Owner();
            owner.setName(name);
            owner.setUser(user);
            owner.setPassword(password);
            try {
                System.out.println(owner.getId());
                OwnerRepo.getInstance().saveOwner(owner);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("./admin.html");
            return;
        }

        if (user != null && password != null) {
            this.doLogin(request, response);
        } else {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute(LoginServlet.LOGIN_TOKEN) != Boolean.TRUE) {
                response.sendRedirect("./oldUserLogin.html");
            } else {
                response.sendRedirect("./admin.html");
            }
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        String verifyCode = (String) request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_VERIFY_CODE);
        if (code == null || !code.equals(verifyCode)) {
            response.sendRedirect("./oldUserLogin.html");
            return;
        }

        try {
            Owner owner = OwnerRepo.getInstance().auth(userName, password);
            if (owner != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(LOGIN_TOKEN, Boolean.TRUE);
                response.sendRedirect("./admin.html");
            } else {
                response.sendRedirect("./oldUserLogin.html");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
