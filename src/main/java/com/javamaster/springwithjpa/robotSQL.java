package com.javamaster.springwithjpa;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "robots")
public class robotSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "info")
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        robotSQL robots = (robotSQL) o;
        return Objects.equals(id, robots.id) &&
                Objects.equals(info, robots.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    public JSONObject toJSONObject()
    {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("info", info);
        return object;
    }

    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}