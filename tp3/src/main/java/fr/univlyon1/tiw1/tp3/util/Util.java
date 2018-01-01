package fr.univlyon1.tiw1.tp3.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/2/17.
 *
 * Utility class
 */
public class Util {

    private Util() {
        /* utility class */
    }

    /**
     * Transforms the object to an string formatted
     *
     * @param obj any object
     * @return the json string.
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Transforms the date to an string formatted.
     *
     * @param date a date
     * @return the string in the format `MM-dd-yyyy HH:mm:ss.SS`
     */
    public static String toStringFormat(final Date date) {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SS");

        return df.format(date);
    }
}
