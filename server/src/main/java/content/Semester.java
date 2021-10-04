package content;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Класс для хранения видов из окна квартиры
 */
public enum Semester implements Serializable {
    SECOND,
    THIRD,
    FOURTH,
    FIFTH;

    public static String ViewToString(){
        return "Список возможных семестров: " + Arrays.toString(Semester.values());
    }
}