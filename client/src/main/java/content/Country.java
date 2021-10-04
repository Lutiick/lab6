package content;

import java.io.Serializable;
import java.util.Arrays;

public enum Country implements Serializable {
    GERMANY,
    ITALY,
    THAILAND;

    public static String ViewToString(){
        return "Список возможных стран: " + Arrays.toString(Country.values());
    }
}
