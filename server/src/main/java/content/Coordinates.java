package content;

import java.io.Serializable;

/**
 * Класс для хранения координат квартиры
 */
public class Coordinates implements Serializable{
    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getStringValue(){ return x + ";" + y; }


    private final Long x; // Максимальное значение поля: 610, Поле не может быть null
    private final float y; // Значение поля должно быть больше -237


}