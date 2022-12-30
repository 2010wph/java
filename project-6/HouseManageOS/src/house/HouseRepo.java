package house;

import db.DBEngine;
import db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HouseRepo {
    private static HouseRepo instance = new HouseRepo();

    private HouseRepo() {}

    public static HouseRepo getInstance() {
        return instance;
    }

    public List<House> getAll() throws SQLException {
        String sql = "SELECT `id`, `owner`, `price`, `area`, `describe` FROM `house`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<House>() {
            @Override
            public House visit(ResultSet rs) throws SQLException {
                return HouseRepo.getHouseFromResultSet(rs);
            }
        });
    }


    public void saveHouse(House house) throws SQLException {
        if (house.getId() > 0) {
            this.updateHouse(house);
        } else {
            System.out.println("house.id= " + house.getId());
            this.insertNewHouse(house);
        }
    }

    private void insertNewHouse(House house) throws SQLException {
        String template = "INSERT INTO `house`(`owner`, `price`, `area`, `describe`)" +
                " VALUE (\"%s\", %s, %s, \"%s\")";
        String sql = String.format(template, house.getOwner(), house.getPrice(), house.getArea(), house.getDescribe());
        DBEngine.getInstance().execute(sql);
    }

    private void updateHouse(House house) throws SQLException {
        String template = "UPDATE `house` SET `owner` = \"%s\", `price` = %s, `area` = %s, `describe` = \"%s\" WHERE `id` = %s";
        String sql = String.format(template, house.getOwner(), house.getPrice(), house.getArea(), house.getDescribe(), house.getId());
        DBEngine.getInstance().execute(sql);
    }

    public void deleteHouse(House house) throws SQLException {
        String template = "DELETE FROM `house` WHERE `id` = %s";
        String sql = String.format(template, house.getId());
        DBEngine.getInstance().execute(sql);
    }

    public void deleteHouse(Long id) throws SQLException {
        String template = "DELETE FROM `house` WHERE `id` = %s";
        String sql = String.format(template, id);
        DBEngine.getInstance().execute(sql);
    }

    public House getById(String id) throws SQLException {
        String sql = String.format("SELECT * FROM `house` WHERE `id` = %s", id);
        List<House> houses = DBEngine.getInstance().query(sql, new RecordVisitor<House>() {

            @Override
            public House visit(ResultSet rs) throws SQLException {
                return HouseRepo.getHouseFromResultSet(rs);
            }
        });
        return houses.size() == 0 ? null : houses.get(0);
    }

    private static House getHouseFromResultSet(ResultSet rs) throws SQLException {
        House house = new House();
        house.setId(rs.getLong("id"));
        house.setOwner(rs.getString("owner"));
        house.setPrice(rs.getFloat("price"));
        house.setArea(rs.getFloat("area"));
        house.setDescribe(rs.getString("describe"));
        return house;
    }


}
