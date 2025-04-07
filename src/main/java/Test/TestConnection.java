package Test;

import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection connection = Util.getConnection()) {
            System.out.println("✅ Подключение к базе данных успешно!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения:");
            e.printStackTrace();
        }
    }
}
