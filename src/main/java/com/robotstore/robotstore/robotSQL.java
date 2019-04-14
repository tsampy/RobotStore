/* robot
    id  : l'identifiant unique de chaque robot, de type serial
	name : texte
	type : texte
	batterylife : en heures, entier
	manufacturer : texte
	wifi : booleen
	bluetooth : booleen
	usb : booleen
	productcode : texte
	depth : en mm, entier
	height : en mm, entier
	width : en mm, entier
	weight : en grammes, entier
	description : texte
*/

package com.robotstore.robotstore;

import org.json.JSONObject;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "robots")
public class robotSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "batterylife")
    private int batterylife;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "wifi")
    private boolean wifi;

    @Column(name = "bluetooth")
    private boolean bluetooth;

    @Column(name = "usb")
    private boolean usb;

    @Column(name = "productcode")
    private String productcode;

    @Column(name = "depth")
    private int depth;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "weight")
    private int weight;

    @Column(name = "description")
    private String description;

    // getters ----------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getBatterylife() {
        return batterylife;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public boolean isUsb() {
        return usb;
    }

    public String getProductcode() {
        return productcode;
    }

    public int getDepth() {
        return depth;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getWeight() {
        return weight;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setBatterylife(int batterylife) {
        this.batterylife = batterylife;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setUsb(boolean usb) {
        this.usb = usb;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // equals and hashCode ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        robotSQL robotSQL = (robotSQL) o;
        return batterylife == robotSQL.batterylife &&
                wifi == robotSQL.wifi &&
                bluetooth == robotSQL.bluetooth &&
                usb == robotSQL.usb &&
                depth == robotSQL.depth &&
                height == robotSQL.height &&
                width == robotSQL.width &&
                weight == robotSQL.weight &&
                Objects.equals(id, robotSQL.id) &&
                Objects.equals(name, robotSQL.name) &&
                Objects.equals(type, robotSQL.type) &&
                Objects.equals(manufacturer, robotSQL.manufacturer) &&
                Objects.equals(productcode, robotSQL.productcode) &&
                Objects.equals(description, robotSQL.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, batterylife, manufacturer, wifi, bluetooth, usb, productcode, depth, height, width, weight, description);
    }

    // JSON object creation
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("name", name);
        object.put("type", type);
        object.put("batterylife", batterylife);
        object.put("manufacturer", manufacturer);
        object.put("wifi", wifi);
        object.put("bluetooth", bluetooth);
        object.put("usb", usb);
        object.put("productcode", productcode);
        object.put("depth", depth);
        object.put("height", height);
        object.put("width", width);
        object.put("weight", weight);
        object.put("description", description);

        return object;
    }

    // JSON formatted toString()
    @Override
    public String toString() {
        return toJSONObject().toString();
    }
}