package content;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Класс для хранения данных о доме, в котором находится квартира
 */
public class Person implements Serializable {
    public Person(String name, int height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }
    public int getHeight() {
        return height;
    }
    public Color getEyeColor() {
        return eyeColor;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public Country getNationality() {
        return nationality;
    }
    public Location getLocation() {
        return location;
    }

    @XmlAttribute(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlAttribute(name = "height")
    private int height; //Значение поля должно быть больше 0
    @XmlAttribute(name = "eyeColor")
    private Color eyeColor; //Поле не может быть null
    @XmlAttribute(name = "hairColor")
    private Color hairColor; //Поле может быть null
    @XmlAttribute(name = "nationality")
    private Country nationality; //Поле может быть null
    @XmlElement(name = "location")
    private Location location; //Поле может быть null

    public String NiceToString() {
        return "Данные админа { " +
                "Имя - " + this.getName() + ", рост - " + this.getHeight() + ", цвет глаз - " + this.getEyeColor() +
                ",\nцвет волос - " + this.getHairColor() + ", национальность - " + this.getNationality() +
                ",\nлокация - " + this.getLocation().getName() + ", X локации - " + this.getLocation().getX() + ", Y локации - " + this.getLocation().getY() +
                " }";
    }
}