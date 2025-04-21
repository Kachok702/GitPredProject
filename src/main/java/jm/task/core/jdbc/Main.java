package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();



        userService.saveUser("Marsel", "Shaikhulov", (byte) 22);
        userService.saveUser("Liza", "Sidorova", (byte) 19);
        userService.saveUser("Dima", "Petrov", (byte) 25);
        userService.saveUser("Liza", "Ivanova", (byte) 20);


        List<User> users2 = userService.getAllUsers();
        System.out.println("Список пользователей:");
        users2.forEach(System.out::println);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
