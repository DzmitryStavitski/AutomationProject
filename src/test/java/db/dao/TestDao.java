package db.dao;

import db.models.Test;
import db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TestDao {
    public void save(Test test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(test);
        tx1.commit();
        session.close();
    }

    public List<Test> findAll() {
        return (List<Test>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From db.models.Test").list();
    }

    public void delete(Test test) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(test);
        tx1.commit();
        session.close();
    }
}
