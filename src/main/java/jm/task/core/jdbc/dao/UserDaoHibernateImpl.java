package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final Util util;

    public UserDaoHibernateImpl(Util util) {
        this.util = util;
    }

    @Override
    public void createUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS " +
                    "users" +
                    " (id               int NOT NULL AUTO_INCREMENT," +
                    " name              VARCHAR(50)," +
                    " lastname          VARCHAR(50)," +
                    " age               TINYINT," +
                    " PRIMARY KEY(id))";

            session.createSQLQuery(sql)
                    .executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF  EXISTS "  +
                    "users";

            session.createSQLQuery(sql)
                    .executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));

            transaction.commit();
        }

        System.out.println("User with Name: " + name + " added to dataBase");
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User tmp = new User();
            tmp.setId(id);

            session.delete(tmp);

            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> result;

        try (Session session = util.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            criteriaQuery.from(User.class);

            result = session.createQuery(criteriaQuery)
                    .getResultList();
        }

        return result;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "TRUNCATE TABLE "  +
                    "users";

            session.createSQLQuery(sql)
                    .executeUpdate();

            transaction.commit();
        }
    }
}
