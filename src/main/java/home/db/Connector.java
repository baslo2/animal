package home.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import home.Settings;

public final class Connector {

    private static final String JDBC_DRIVER_POSTGRESQL = "org.postgresql.Driver";
    private static final String CONNECTION_URL_POSTGRESQL = "jdbc:postgresql://%s:%s/%s";

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER_POSTGRESQL);
            var url = CONNECTION_URL_POSTGRESQL
                .formatted(Settings.getHost(), Settings.getPort(), Settings.getDbName());
            var props = new Properties();
            props.setProperty("user", Settings.getUserName());
            props.setProperty("password", Settings.getUserPassword());
            return DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("didn't find the JDBC driver\n" + e.getMessage());
        } catch (SQLException e) {
            throw new IllegalStateException("data base connection error\n" + e.getMessage());
        }
    }

    private Connector() {
    }
}
