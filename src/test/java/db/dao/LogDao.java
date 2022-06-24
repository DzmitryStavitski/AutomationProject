package db.dao;

import db.models.Log;
import db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LogDao {
    public void save(Log log) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(log);
        tx1.commit();
        session.close();
    }

    public List<Log> findAll() {
        return (List<Log>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From db.models.Log").list();
    }
}
