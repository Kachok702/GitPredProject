package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeMutationQuery(
                    "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)"
            ).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создалась");
        } catch (IOException e) {
            System.out.println("Таблица не создалась по причине: " + e);
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeMutationQuery(
                    "DROP TABLE IF EXISTS users"
            ).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалилась");
        } catch (IOException e) {
            System.out.println("Таблица не удалилась по причине: " + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            Transaction transaction = session.beginTransaction();
           User user = new User(name, lastName, age);
           session.persist(user);
            transaction.commit();
            System.out.println("Пользователь добавлен");
        } catch (IOException e) {
            System.out.println("Пользователь не добавлен по причине: " + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
            System.out.println("Пользователь удален");
        } catch (Exception e) {
            System.out.println("Пользователь не удален по причине: " + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = Util.getConnectionHibernate().openSession()) {
           Query<User> query = session.createQuery("from User", User.class);
            System.out.println("Пользователи выведены");
            return query.list();
        } catch (IOException e) {
            System.out.println("Пользователи не выведены" + e);
        return Collections.emptyList();}
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            Transaction transaction = session.beginTransaction();
                        session.createNativeMutationQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица почищена");
        } catch (IOException e) {
            System.out.println("Таблица не почищена по причине: " + e);
        }

    }
}
