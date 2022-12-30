package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine = DBEngine.getInstance();
//        String sql = "delete from book where id = 2";
//        engine.execute(sql);
//        String sql = "SELECT `id`, `name`, `author`, `price`, `describe` FROM book";
//        List<Book> books = engine.query(sql, new RecordVisitor<Book>() {
//            @Override
//            public Book visit(ResultSet rs) throws SQLException {
//                Book book = new Book();
//                book.setId(rs.getLong("id"));
//                book.setName(rs.getString("name"));
//                book.setAuthor(rs.getString("author"));
//                book.setPrice(rs.getFloat("price"));
//                book.setDescribe(rs.getString("describe"));
//
//                return book;
//            }
//        });
//        for (Book book: books) {
//            System.out.println(String.format("%s, %s, %s, %s, %s",
//                    book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getDescribe()));
//        }
    }
}
