package kz.edu.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CardEntity")
@Table(name = "cards")
public class Card implements Serializable
{
    private long card_id;
    private String card_num;
    private float kzt;
    private float usd;
    private float eur;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getCard_id()
    {
        return this.card_id;
    }
    public void setCard_id(long card_id)
    {
        this.card_id = card_id;
    }

    @Column(name = "card_num")
    public String getCard_num()
    {
        return this.card_num;
    }
    public void setCard_num(String card_num) {this.card_num = card_num;}

    @Column(name = "kzt")
    public float getKzt()
    {
        return this.kzt;
    }
    public void setKzt(float kzt)
    {
        this.kzt = kzt;
    }

    @Column(name = "usd")
    public float getUsd()
    {
        return this.usd;
    }
    public void setUsd(float usd)
    {
        this.usd = usd;
    }

    @Column(name = "eur")
    public float getEur()
    {
        return this.eur;
    }
    public void setEur(float eur)
    {
        this.eur = eur;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
}
