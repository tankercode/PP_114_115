package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Util util;

    public UserDaoJDBCImpl(Util util) {
        this.util = util;
    }

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS "  +
                "users" +
                "(id                int NOT NULL AUTO_INCREMENT, " +
                "name               VARCHAR(50), " +
                "lastname           VARCHAR(50), " +
                "age                TINYINT, " +
                "PRIMARY KEY(id))";

        try (Statement stmt = util.getConnection().createStatement()){
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF  EXISTS "  +
                "users";

        try (Statement stmt = util.getConnection().createStatement()){
            stmt.execute(sqlDrop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCreateUser = "INSERT INTO " +
                "users" +
                " ( Name, LastName, Age) Values (?, ?, ?)";

        try (PreparedStatement stmt = util.getConnection().prepareStatement(sqlCreateUser)){
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);

            stmt.executeUpdate();

            System.out.println("User with Name: " + name + " added to dataBase");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlDeleteUser = "DELETE FROM " +
                "users" +
                " WHERE id = " +
                id;

        try (Statement stmt = util.getConnection().createStatement()){
            stmt.execute(sqlDeleteUser);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        String sqlGetAllUser = "SELECT * FROM " +
                "users";

        List<User> users = new ArrayList<>();

        try (Statement stmt = util.getConnection().createStatement()){
            ResultSet resultSet = stmt.executeQuery(sqlGetAllUser);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        String sqlClear = "TRUNCATE TABLE "  +
                "users";

        try (Statement stmt = util.getConnection().createStatement()){
            stmt.execute(sqlClear);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
