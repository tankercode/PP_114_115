package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private final SessionFactory sessionFactory;

    private final String USER_NAME = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/userschema";

    protected final String TABLE_NAME = "users";

    public Connection getConnection() {

        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public Util() {
        Properties properties = new Properties();
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, USER_NAME);
        properties.put(Environment.PASS, PASSWORD);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");


        Configuration configuration = new Configuration()
        .setProperties(properties)
        .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
