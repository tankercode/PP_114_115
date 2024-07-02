package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private int tableIndex = 0;

    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS "  +
                TABLE_NAME +
                "(id                int NOT NULL AUTO_INCREMENT, " +
                "name               VARCHAR(50), " +
                "lastname           VARCHAR(50), " +
                "age                int, " +
                "PRIMARY KEY(id))";

        try (Statement stmt = getConnection().createStatement()){
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF  EXISTS "  +
                TABLE_NAME;

        try (Statement stmt = getConnection().createStatement()){
            stmt.execute(sqlDrop);
            tableIndex = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCreateUser = "INSERT INTO " +
                TABLE_NAME +
                " ( Id, Name, LastName, Age) Values (?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sqlCreateUser)){
            stmt.setInt(1, ++tableIndex);
            stmt.setString(2, name);
            stmt.setString(3, lastName);
            stmt.setByte(4, age);

            stmt.executeUpdate();

            System.out.println("User with Name: " + name + " added to dataBase");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sqlDeleteUser = "DELETE FROM " +
                TABLE_NAME +
                " WHERE id = " +
                id;

        try (Statement stmt = getConnection().createStatement()){
            stmt.execute(sqlDeleteUser);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        String sqlDeleteUser = "SELECT * FROM " +
                TABLE_NAME;

        List<User> users = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement()){
            ResultSet resultSet = stmt.executeQuery(sqlDeleteUser);
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
                TABLE_NAME;

        try (Statement stmt = getConnection().createStatement()){
            stmt.execute(sqlClear);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
