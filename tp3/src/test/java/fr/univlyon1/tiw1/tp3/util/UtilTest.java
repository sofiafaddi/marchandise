package fr.univlyon1.tiw1.tp3.util;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/4/17.
 */
public class UtilTest {

    @Test
    public void asJsonString() {
        Entrepot entrepot = new EntrepotEntity("test", 40);
        String expected = "{\"nom\":\"test\",\"capacite\":40.0,\"marchandisesStockees\":[],\"occupation\":0.0}";
        String founded = Util.asJsonString(entrepot);

        JSONAssert.assertEquals(expected, founded, false);
    }

    @Test
    public void toStringFormatTest() throws ParseException {
        String string = "January 2, 2010 08:40:00.00";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss.SS");
        Date date = format.parse(string);

        assertEquals(Util.toStringFormat(date), "01-02-2010 08:40:00.00");
    }
}
