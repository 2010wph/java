package owner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/saveOwner")
public class SaveOwnerServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        Owner owner = new Owner();
        owner.setName(name);
        owner.setUser(user);
        owner.setPassword(password);

        if (id != null) {
            owner.setId(Long.valueOf(id));
        }

        String message;
        try {
            OwnerRepo.getInstance().saveOwner(owner);
            message = "提交用户信息保存成功";
        } catch (SQLException e) {
            message = "提交用户信息保存失败";
        }

        try (Writer writer = response.getWriter()) {
            String html = "<center><h1>%s</h1><br><br>" +
                    "<a href='./submit-owner.html'>再 次 录 入 用 户</a><br>" +
                    "<a href='./listOwner'>显 示 用 户 列 表</a><br>" +
                    "<a href='./index.html'>返 回 首 页</a><br>" +
                    "</center>";
            writer.write(String.format(html, message));
        }
    }
}
