package dreamteam.dao;

import dreamteam.dto.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dreamteam.dto.User;
import dreamteam.exception.InvalidFormatData;
import org.hibernate.*;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public UserDAO() {
    }

    Session session = sessionFactory.openSession();
    Transaction transaction = null;

    public List<User> getAllUsers() {
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        session.close();
        return users;
    }

    public User saveUser(User user) {

        try {
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    public boolean updateUser(User user) {


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM User", User.class).setCacheable(true).getResultList();
        session.close();
        return users;
    }

    public User saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new InvalidFormatData("Email already exists");
        } finally {
            session.close();
        }
        return user;
    }

    public boolean updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();

            session.close();

            return true;

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }

    public boolean deleteUser(Integer id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

            session.close();


        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return false;
    }


}

