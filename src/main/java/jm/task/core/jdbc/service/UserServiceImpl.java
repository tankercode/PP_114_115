package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl uJDBC = new UserDaoJDBCImpl(new Util());

    public void createUsersTable() {
        uJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        uJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        uJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        uJDBC.cleanUsersTable();
    }
}
