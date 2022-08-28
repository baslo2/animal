package home.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationsDB {

    private final static String SELECT_ALL = "SELECT * FROM public.my_table;";
    private final static String INSERT = "INSERT INTO public.my_table (name) VALUES (?)";

    private final static String COL_NAME = "name";
    private final static String COL_ID = "id";

    public static void writeOne(String name) {
        try (var conn = Connector.getConnection();
                var pstmt = conn.prepareStatement(INSERT)) {
            pstmt.setString(1, name);
            if (pstmt.executeUpdate() != 1) {
                throw new SQLException("didn't write in date base");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("database write error\n" + e.getMessage(), e);
        }
    }

    public static void writeAll(List<String> paramList) {
        try (var conn = Connector.getConnection();
                var pstmt = conn.prepareStatement(INSERT)) {
            int operationCount = 0;
            for (String param : paramList) {
                pstmt.setString(1, param);
                pstmt.addBatch();
                operationCount++;

                // Execute every 1000 items.
                if (operationCount % 1000 == 0 || operationCount == paramList.size()) {
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("database write error\n" + e.getMessage(), e);
        }
    }

    public static List<String> readAll() {
        try (var conn = Connector.getConnection();
                var stmt = conn.createStatement();
                var res = stmt.executeQuery(SELECT_ALL)) {
            var dataObjs = new ArrayList<String>();
            while (res.next()) {
                dataObjs.add(res.getObject(COL_ID) + " " + res.getObject(COL_NAME));
            }
            return dataObjs;
        } catch (SQLException e) {
            throw new IllegalStateException("database read error\n" + e.getMessage(), e);
        }
    }
}
