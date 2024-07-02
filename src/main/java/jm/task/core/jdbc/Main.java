package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Asdf", "Isis", (byte) 1);
        service.saveUser("Dsdf", "#sis", (byte) 3);
        service.saveUser("Ssdf", "I$is", (byte) 5);
        service.saveUser("Esdf", "@is", (byte) 9);

        System.out.println(Arrays.toString(service.getAllUsers().toArray()));

        service.cleanUsersTable();

        service.dropUsersTable();

        service.closeConnection();
    }
}
