package content;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Класс для хранения видов из окна квартиры
 */
public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION,
    FULL_TIME_EDUCATION,
    EVENING_CLASSES;

    public static String ViewToString(){
        return "Список возможных форм обучений: " + Arrays.toString(FormOfEducation.values());
    }
}