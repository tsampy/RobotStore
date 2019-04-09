package com.robotstore.robotstore;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "test")
@Table(name = "mainTable")
public class minimumInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        minimumInfo users = (minimumInfo) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(name, users.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public JSONObject toJSONObject()
    {
        JSONObject object = new JSONObject();
        object.put("name", name);

        return object;
    }

    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}
