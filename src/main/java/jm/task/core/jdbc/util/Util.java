package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String IP = "localhost";
    private final String PORT = "3306";
    protected final String SCHEMA_NAME = "userschema";
    protected final String TABLE_NAME = "usertable";

    private final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + SCHEMA_NAME;
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private final Connection connection;

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
