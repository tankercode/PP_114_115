package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoHibernateImpl uHibernate = new UserDaoHibernateImpl(new Util());

    public void createUsersTable() {
        uHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        uHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        uHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        uHibernate.cleanUsersTable();
    }
}
