/*
    idrobot : identifiant robot, entier
	price : entier
	promotion : pourcentage, entier
	deliveryprice : entier
	nb : entier
	available : nombre de jours avant disponibilite, entier
* */

package com.robotstore.robotstore;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "catalog")
public class catalogSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idrobot")
    private int idrobot;

    @Column(name = "price")
    private int price;

    @Column(name = "promotion")
    private int promotion;

    @Column(name = "deliveryprice")
    private int deliveryprice;

    @Column(name = "nb")
    private int nb;

    @Column(name = "available")
    private int available;

    // getters ----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public int getIdrobot() {
        return idrobot;
    }

    public int getPrice() {
        return price;
    }

    public int getPromotion() {
        return promotion;
    }

    public int getDeliveryprice() {
        return deliveryprice;
    }

    public int getNb() {
        return nb;
    }

    public int getAvailable() {
        return available;
    }

    // setters ----------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setIdrobot(int idrobot) {
        this.idrobot = idrobot;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setDeliveryprice(int deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    // equals and hashCode ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        catalogSQL that = (catalogSQL) o;
        return idrobot == that.idrobot &&
                price == that.price &&
                promotion == that.promotion &&
                deliveryprice == that.deliveryprice &&
                nb == that.nb &&
                available == that.available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrobot, price, promotion, deliveryprice, nb, available);
    }

    // JSON object creation
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("idrobot", idrobot);
        object.put("price", price);
        object.put("promotion", promotion);
        object.put("deliveryprice", deliveryprice);
        object.put("nb", nb);
        object.put("available", available);

        return object;
    }

    // JSON formatted toString()
    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}