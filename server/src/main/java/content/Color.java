package content;

import java.io.Serializable;
import java.util.Arrays;

public enum Color implements Serializable{
    RED,
    BLACK,
    BLUE,
    ORANGE,
    BROWN;

    public static String ViewToString(){
        return "Список возможных цветов: " + Arrays.toString(Color.values());
    }
}