package content;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Класс для хранения вида транспорта собственника квартиры
 */
public class Location implements Serializable {

    public Location(Integer x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Location() {}

    @XmlAttribute(name = "x")
    private Integer x; //Поле не может быть null
    @XmlAttribute(name = "y")
    private float y;
    @XmlAttribute(name = "name")
    private String name; //Поле не может быть null

    public Integer getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}