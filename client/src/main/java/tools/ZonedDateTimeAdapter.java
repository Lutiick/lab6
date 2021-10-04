package tools;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Вспомогательный класс для корректной сериализации/десериализации класса LocalDateTime (класс времени)
 */
public class ZonedDateTimeAdapter extends XmlAdapter<String,ZonedDateTime> {
    public ZonedDateTime unmarshal(String v){
        ZoneId zoneid = ZoneId.of("Europe/Moscow");
        return ZonedDateTime.of(
                Integer.parseInt(v.split(",")[0].split("\\.")[2]),
                Integer.parseInt(v.split(",")[0].split("\\.")[1]),
                Integer.parseInt(v.split(",")[0].split("\\.")[1]),
                Integer.parseInt(v.split(",")[1].split(":")[0]),
                Integer.parseInt(v.split(",")[1].split(":")[1]),
                Integer.parseInt(v.split(",")[1].split(":")[2]),
                0,
                zoneid
        );
    }
    public String marshal(ZonedDateTime l) {
        return l.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss"));
    }
}
