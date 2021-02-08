package kz.edu.model;

import javax.persistence.*;
import java.io.Serializable;

public class History implements Serializable
{
    private long history_id;
    private Card card;
    private float income;
    private float outcome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    public long getHistory_id()
    {
        return this.history_id;
    }
    public void setHistory_id(long history_id)
    {
        this.history_id = history_id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    public Card getCard()
    {
        return card;
    }
    public void setCard(Card card)
    {
        this.card = card;
    }

    @Column(name = "income")
    public float getIncome()
    {
        return this.income;
    }
    public void setIncome(float income)
    {
        this.income = income;
    }

    @Column(name = "outcome")
    public float getOutcome()
    {
        return this.outcome;
    }
    public void setOutcome(float outcome)
    {
        this.outcome = outcome;
    }
}
