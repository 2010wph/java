package owner;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listOwner")
public class ListOwnerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Owner> owners = OwnerRepo.getInstance().getAll();
            response.setContentType("text/html; charset=UTF-8");
            try(Writer writer = response.getWriter()) {
                writer.write("<center style=\"margin-top:5em\">\n");
                writer.write("<h1>欢迎访问用户管理系统</h1>\n");

                writer.write("<table width='55%' border='0' cellpadding=4>");
                for(int i=0; i < owners.size(); i++) {
                    Owner owner = owners.get(i);
                    if (i % 2 == 0) {
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    } else {
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='30px'>%s</td>", owner.getId()));
                    writer.write(String.format("<td width='150px'>%s</td>", owner.getName()));
                    writer.write(String.format("<td width='100px'>%s</td>", owner.getUser()));
                    writer.write(String.format("<td width='60px'>%s</td>", owner.getPassword()));
                    writer.write(String.format("<td><a href='./deleteOwner?id=%s'>删除</a></td>", owner.getId()));
                    writer.write(String.format("<td><a href='./updateOwner?id=%s'>修改</a></td>", owner.getId()));

                    writer.write("</tr>");
                }
                writer.write("</table><br><br>\n\n");

                writer.write("<a href='admin.html'>返 回 首 页</a>\n");
                writer.write("</center>\n");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
