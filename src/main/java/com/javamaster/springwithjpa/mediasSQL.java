/*
    idrobot : identifiant robot
	link : texte
	photo : photo ou video, booleen
	caption : texte
*/

package com.javamaster.springwithjpa;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "medias")
public class mediasSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idrobot")
    private int idrobot;

    @Column(name = "link")
    private String link;

    @Column(name = "photo")
    private boolean photo;

    @Column(name = "caption")
    private String caption;

    // getters ----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public int getIdrobot() {
        return idrobot;
    }

    public String getLink() {
        return link;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isPhoto() {
        return photo;
    }

    // setters ----------------------------------------------------------------
    public void setId(Long id) {
        this.id = id;
    }

    public void setIdrobot(int idrobot) {
        this.idrobot = idrobot;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    // equals and hashCode ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        mediasSQL mediasSQL = (mediasSQL) o;
        return idrobot == mediasSQL.idrobot &&
                photo == mediasSQL.photo &&
                Objects.equals(link, mediasSQL.link) &&
                Objects.equals(caption, mediasSQL.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrobot, link, photo, caption);
    }

    // JSON object creation
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("idrobot", idrobot);
        object.put("link", link);
        object.put("photo", photo);
        object.put("caption", caption);

        return object;
    }

    // JSON formatted toString()
    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}