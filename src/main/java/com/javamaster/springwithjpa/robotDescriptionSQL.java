/* robot description
    id  : l'identifiant unique de chaque robot, de type serial
	name : texte
	manufacturer : texte
	description : texte
*/

package com.javamaster.springwithjpa;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "robots")
public class robotDescriptionSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "description")
    private String description;

    // getters ----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getDescription() {
        return description;
    }

    // setters ----------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // equals and hashCode ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        robotDescriptionSQL that = (robotDescriptionSQL) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manufacturer, description);
    }

    // JSON object creation
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("name", name);
        object.put("manufacturer", manufacturer);
        object.put("description", description);

        return object;
    }

    // JSON formatted toString()
    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}