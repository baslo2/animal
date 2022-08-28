package home.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {

    private static final String JDBC_DRIVER_POSTGRESQL = "org.postgresql.Driver";
    private static final String CONNECTION_URL_POSTGRESQL = "jdbc:postgresql://localhost:5432/elf";

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER_POSTGRESQL);
            var props = new Properties();
            props.setProperty("user", "test_user");
            props.setProperty("password", "test_password");
            return DriverManager.getConnection(CONNECTION_URL_POSTGRESQL, props);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("didn't find the JDBC driver\n" + e.getMessage());
        } catch (SQLException e) {
            throw new IllegalStateException("data base connection error\n" + e.getMessage());
        }
    }

    private Connector() {
    }
}
