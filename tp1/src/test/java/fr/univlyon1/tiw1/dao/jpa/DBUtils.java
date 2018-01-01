package fr.univlyon1.tiw1.dao.jpa;

import fr.univlyon1.tiw1.metier.spec.dao.EntrepotDAO;
import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ecoquery on 12/07/2017.
 */
public class DBUtils {

    public static final String DEFAULT_URL = "jdbc:h2:mem:mytestdb";
    public static final String PERSISTENCE_UNIT_NAME = "pu-test";

    public static Connection create(String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        RunScript.execute(connection, new InputStreamReader(EntrepotDAO.class.getResourceAsStream("/entrepots.sql")));
        return connection;
    }

    public static void clear(Connection connection) throws SQLException {
        RunScript.execute(connection, new InputStreamReader(DBUtils.class.getResourceAsStream("/clear.sql")));
    }
}
