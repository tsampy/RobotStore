package com.javamaster.springwithjpa;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client")
public class clientSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idclient")
    private int idclient;

    @Column(name = "login")
    private String login;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "mail")
    private String mail;

    // getters ----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public int getIdclient() {
        return idclient;
    }

    public String getLogin() {
        return login;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getMail() {
        return mail;
    }

    // setters ----------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // equals and hashCode ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        clientSQL clientSQL = (clientSQL) o;
        return idclient == clientSQL.idclient &&
                Objects.equals(id, clientSQL.id) &&
                Objects.equals(login, clientSQL.login) &&
                Objects.equals(pw, clientSQL.pw) &&
                Objects.equals(name, clientSQL.name) &&
                Objects.equals(lastname, clientSQL.lastname) &&
                Objects.equals(address, clientSQL.address) &&
                Objects.equals(zip, clientSQL.zip) &&
                Objects.equals(city, clientSQL.city) &&
                Objects.equals(country, clientSQL.country) &&
                Objects.equals(mail, clientSQL.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idclient, login, pw, name, lastname, address, zip, city, country, mail);
    }

    // JSON object creation
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("idclient", idclient);
        object.put("login", login);
        object.put("pw", pw);
        object.put("name", name);
        object.put("lastname", lastname);
        object.put("address", address);
        object.put("zip", zip);
        object.put("city", city);
        object.put("country", country);
        object.put("mail", mail);

        return object;
    }

    // JSON formatted toString()
    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}