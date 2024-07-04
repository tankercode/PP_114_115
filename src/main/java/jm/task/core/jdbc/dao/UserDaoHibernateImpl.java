package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_NAME +
                    " (id               int NOT NULL AUTO_INCREMENT," +
                    " name              VARCHAR(50)," +
                    " lastname          VARCHAR(50)," +
                    " age               TINYINT," +
                    " PRIMARY KEY(id))";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF  EXISTS "  +
                    TABLE_NAME;

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));

            transaction.commit();
        }

        System.out.println("User with Name: " + name + " added to dataBase");
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
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

        try (Session session = getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            criteriaQuery.from(User.class);

            result = session.createQuery(criteriaQuery).getResultList();
        }

        return result;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "TRUNCATE TABLE "  +
                    TABLE_NAME;

            Query query = session.createSQLQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }
}
