import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Repeatable;
import java.nio.charset.Charset;
import java.sql.SQLException;

@WebServlet("/saveBook")
public class SaveBookServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String describe = request.getParameter("describe");

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(Float.valueOf(price));
        book.setDescribe(describe);
        System.out.println(String.format("%s, %s, %s, %s", book.getName(), book.getAuthor(), book.getPrice(), book.getDescribe()));

        String message = null;
        try {
            DBUtils.saveBook(book);
            message = "提交信息保存成功";
        } catch (SQLException e) {
            message = "提交信息保存失败";
        }

        try (Writer writer = response.getWriter()) {
            String html = "<center><h1>%s</h1></center><br>" +
                    "<a href='./submit-book.html'>录入新图书</a>";
            writer.write(String.format(html, message));
        }
    }
}
