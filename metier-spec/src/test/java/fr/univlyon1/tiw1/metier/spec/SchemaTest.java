package fr.univlyon1.tiw1.metier.spec;

import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import org.everit.json.schema.loader.SchemaLoader;
import org.h2.tools.RunScript;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class SchemaTest {

    @Test
    public void testXMLSchema() throws SAXException {
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new StreamSource(EntrepotDAO.class
                        .getResourceAsStream("/entrepots.xsd")));
        assertNotNull(schema);
    }

    @Test
    public void testJSONSchema() throws IOException {
        try (InputStream inputStream = EntrepotDAO.class.getResourceAsStream("/entrepots.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);
            assertNotNull(schema);
        }
    }

    @Test
    public void testSQLSchema() throws IOException, SQLException {
        try (Reader sql = new InputStreamReader(EntrepotDAO.class.getResourceAsStream("/entrepots.sql"))) {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
            RunScript.execute(connection, sql);
        }
    }
}
