package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class Util {

    private final SessionFactory sessionFactory;

    protected final String TABLE_NAME = "users";

    public Util() {
        Properties properties = new Properties();
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/userschema");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
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
