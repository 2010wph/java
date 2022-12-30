package owner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/updateOwner")
public class updateOwner extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Owner owner;
        try {
            owner = OwnerRepo.getInstance().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>编辑用户信息</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <center>\n" +
                "    <div style=\"margin-top:5em; padding: 2em;text-align:center; width:60%; background-color:#EEEEEE\">\n" +
                "      <h2>编辑用户信息</h2>\n" +
                "      <form action=\"./saveOwner\" method=\"post\">\n" +
                "        <input type=\"hidden\" name=\"id\" value=\"" + owner.getId() + "\"><br><br>\n" +
                "        姓 名： <input type=\"text\" name=\"name\" value=\"" + owner.getName() + "\"><br><br>\n" +
                "        用 户： <input type=\"text\" name=\"user\" value=\"" + owner.getUser() + "\"><br><br>\n" +
                "        密 码： <input type=\"text\" name=\"password\" value=\"" + owner.getPassword() + "\"><br><br>\n" +
                "        <input type=\"submit\" value=\" 提 交 信 息\">\n" +
                "      </form>\n" +
                "    </div>\n" +
                "  </center>\n" +
                "</body>\n" +
                "</html>";
        response.setContentType("text/html; Charset=utf8");
        try (Writer writer = response.getWriter()) {
            writer.write(html);
        }
    }
}
