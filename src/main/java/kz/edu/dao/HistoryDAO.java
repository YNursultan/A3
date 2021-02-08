package kz.edu.dao;

import com.google.gson.Gson;
import kz.edu.model.Card;
import kz.edu.model.History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class HistoryDAO {

    private SessionFactory sessionFactory;
    Session session;
    List<History> historyListList;

    @Autowired
    public HistoryDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    public List<History> getHistoryListList()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<History> criteria = builder.createQuery(History.class);
            Root<History> root = criteria.from(History.class);
            criteria.select(root);
            Query<History> query = session.createQuery(criteria);
            historyListList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return historyListList;
    }
    public History getHistory(long id)
    {
        session = sessionFactory.openSession();
        session.beginTransaction();
        History history;
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<History> q1 = builder.createQuery(History.class);
            Root<History> root = q1.from(History.class);

            Predicate predicateBook = builder.equal(root.get("h_id"), id);
            history = session.createQuery(q1.where(predicateBook)).getSingleResult();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return history;
    }
    public void addHistory(History history)
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(history);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
    public void updateHistory(History history)
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(history);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
    public void deleteHistory(long hId)
    {
        System.out.println("delete " + hId);
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            History history = session.find(History.class, hId);
            System.out.println("The history to be deleted: " + history.getHistory_id());
            session.remove(history);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
}
