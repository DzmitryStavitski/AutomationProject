package db.dao;

import db.models.Attachment;
import db.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AttachmentDao {
    public void save(Attachment attachment) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(attachment);
        tx1.commit();
        session.close();
    }

    public List<Attachment> findAll() {
        return (List<Attachment>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From db.models.Test").list();
    }
}
