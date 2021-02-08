package kz.edu.dao;

import com.google.gson.Gson;
import kz.edu.model.Card;
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
public class CardDAO {

    private SessionFactory sessionFactory;
    Session session;
    List<Card> cardsList;
    @Autowired
    public CardDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    public List<Card> getCardsList()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Card> criteria = builder.createQuery(Card.class);
            Root<Card> root = criteria.from(Card.class);
            criteria.select(root);
            Query<Card> query = session.createQuery(criteria);
            cardsList = query.getResultList();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return cardsList;
    }
    public Card getCard(long id)
    {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Card card;
        try
        {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Card> q1 = builder.createQuery(Card.class);
            Root<Card> root = q1.from(Card.class);

            Predicate predicateBook = builder.equal(root.get("id"), id);
            card = session.createQuery(q1.where(predicateBook)).getSingleResult();
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
        return card;
    }
    public void addCard(Card card)
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(card);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
    public void updateCard(Card card)
    {
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(card);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
    public void deleteCard(long bookId)
    {
        System.out.println("delete " + bookId);
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Card card = session.find(Card.class, bookId);
            System.out.println("The card to be deleted: " + card.getCard_num());
            session.remove(card);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
}
