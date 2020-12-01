package edu.javeriana.touresbalon.reserva.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class Utilities {

    private Utilities() {

    }

    public static <T> List<T> getObjectList(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    public static Object getObject(String jsonString, Type type) {
        return new Gson().fromJson(jsonString, type);
    }

    public static Timestamp currentTimestampUTC() {
        return Timestamp.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime());
    }
}
