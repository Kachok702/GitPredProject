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
        System.out.println("User с именем – Marsel добавлен в базу данных" );
        userService.saveUser("Liza", "Sidorova", (byte) 19);
        System.out.println("User с именем – Liza добавлен в базу данных" );
        userService.saveUser("Dima", "Petrov", (byte) 25);
        System.out.println("User с именем – Dima добавлен в базу данных" );
        userService.saveUser("Liza", "Ivanova", (byte) 20);
        System.out.println("User с именем – Liza добавлен в базу данных" );

        List<User> users = userService.getAllUsers();
        System.out.println("Список пользователей:");
        users.forEach(System.out::println);
userService.cleanUsersTable();
userService.dropUsersTable();
    }
}
