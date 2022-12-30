package house;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/saveHouse")
public class SaveHouseServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String price = request.getParameter("price");
        String area = request.getParameter("area");
        String describe = request.getParameter("describe");

        House house = new House();
        house.setOwner(owner);
        house.setPrice(Float.valueOf(price));
        house.setArea(Float.valueOf(area));
        house.setDescribe(describe);
        System.out.println(String.format("%s, %s, %s, %s", house.getOwner(), house.getPrice(), house.getArea(), house.getDescribe()));

        if (id != null) {
            house.setId(Long.valueOf(id));
        }

        String message = null;
        try {
            HouseRepo.getInstance().saveHouse(house);
            message = "提交房屋信息保存成功";
        } catch (SQLException e) {
            message = "提交房屋信息保存失败";
        }

        try (Writer writer = response.getWriter()) {
            String html = "<center><h1>%s</h1><br><br>" +
                    "<a href='./submit-house.html'>再 次 录 入</a><br>" +
                    "<a href='./listHouse'>显 示 列 表</a><br>" +
                    "<a href='./index.html'>返 回 首 页</a><br>" +
                    "</center>";
            writer.write(String.format(html, message));
        }
    }
}
