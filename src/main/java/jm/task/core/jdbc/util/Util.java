package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inputStream);
            Connection connection = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
            return connection;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   public static SessionFactory getConnectionHibernate() throws IOException {
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

       Properties properties = new Properties();
       properties.load(classLoader.getResourceAsStream("hibernate.properties"));

       SessionFactory sessionFactory = new Configuration()
               .addProperties(properties)
               .addAnnotatedClass(User.class)
               .buildSessionFactory();
       return sessionFactory;
    }

}

