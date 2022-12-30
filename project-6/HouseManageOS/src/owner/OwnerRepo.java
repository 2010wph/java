package owner;

import db.DBEngine;
import db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OwnerRepo {
    private static OwnerRepo instance = new OwnerRepo();

    private OwnerRepo() {}

    public static OwnerRepo getInstance() {
        return instance;
    }

    public List<Owner> getAll() throws SQLException {
        String sql = "SELECT `id`, `name`, `user`, `password` FROM `owner`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<Owner>() {
            @Override
            public Owner visit(ResultSet rs) throws SQLException {
                return OwnerRepo.getOwnerFromResultSet(rs);
            }
        });
    }


    public void saveOwner(Owner owner) throws SQLException {
        if (owner.getId() > 0) {
            this.updateOwner(owner);
        } else {
            this.insertNewOwner(owner);
        }
    }

    private void insertNewOwner(Owner owner) throws SQLException {
        String template = "INSERT INTO `owner`(`name`, `user`, `password`)" +
                " VALUE (\"%s\", \"%s\", \"%s\")";
        String sql = String.format(template, owner.getName(), owner.getUser(), owner.getPassword());
        DBEngine.getInstance().execute(sql);
    }

    private void updateOwner(Owner owner) throws SQLException {
        String template = "UPDATE `owner` SET `name` = \"%s\", `user` = \"%s\", `password` = \"%s\" WHERE `id` = %s";
        String sql = String.format(template, owner.getName(), owner.getUser(), owner.getPassword(), owner.getId());
        System.out.println(sql);
        DBEngine.getInstance().execute(sql);
    }

    public void deleteOwner(Long id) throws SQLException {
        String template = "DELETE FROM `owner` WHERE `id` = %s";
        String sql = String.format(template, id);
        DBEngine.getInstance().execute(sql);
    }

    public Owner getById(String id) throws SQLException {
        String sql = String.format("SELECT * FROM `owner` WHERE `id` = %s", id);
        List<Owner> owners = DBEngine.getInstance().query(sql, new RecordVisitor<Owner>() {

            @Override
            public Owner visit(ResultSet rs) throws SQLException {
                return OwnerRepo.getOwnerFromResultSet(rs);
            }
        });
        return owners.size() == 0 ? null : owners.get(0);
    }

    private static Owner getOwnerFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getLong("id"));
        owner.setName(rs.getString("name"));
        owner.setUser(rs.getString("user"));
        owner.setPassword(rs.getString("password"));
        return owner;
    }

    public Owner auth(String user, String password) throws SQLException {
        String template = "SELECT * FROM `owner` WHERE `user` = \"%s\" AND `password` = \"%s\"";
        List<Owner> owners = DBEngine.getInstance().query(
                String.format(template, user, password), new RecordVisitor<Owner>() {
                    @Override
                    public Owner visit(ResultSet rs) throws SQLException {
                        return OwnerRepo.getOwnerFromResultSet(rs);
                    }
                });
        return owners.size() == 0 ? null : owners.get(0);
    }
}
