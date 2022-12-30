package house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/updateHouse")
public class updateHouse extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        House house = null;
        try {
            house = HouseRepo.getInstance().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My house.House Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <center>\n" +
                "    <div style=\"margin-top:5em; padding: 2em;text-align:center; width:60%; background-color:#EEEEEE\">\n" +
                "      <h2>编辑房屋信息</h2>\n" +
                "      <form action=\"./saveHouse\" method=\"post\">\n" +
                "        <input type=\"hidden\" name=\"id\" value=\"" + house.getId() + "\"><br><br>\n" +
                "        主 人： <input type=\"text\" name=\"owner\" value=\"" + house.getOwner() + "\"><br><br>\n" +
                "        价 格： <input type=\"text\" name=\"price\" value=\"" + house.getPrice() + "\"><br><br>\n" +
                "        面 积： <input type=\"text\" name=\"area\" value=\"" + house.getArea() + "\"><br><br>\n" +
                "        简 介： <textarea name=\"describe\" rows=\"4\" cols=\"22\">"+ house.getDescribe() + "</textarea> <br><br>\n" +
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
