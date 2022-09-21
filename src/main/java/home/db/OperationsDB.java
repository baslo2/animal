package home.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import home.model.Animal;
import home.model.AnimalType;

public final class OperationsDB {

    private final static String SELECT_ALL = "SELECT * FROM public.t_animals;";
    private final static String INSERT = "INSERT INTO public.t_animals (type, age, name) VALUES (?, ?, ?)";
    private final static String DELETE_ALL = "TRUNCATE TABLE public.t_animals";

    private final static String COL_ID = "id";
    private final static String COL_TYPE = "type";
    private final static String COL_AGE = "age";
    private final static String COL_NAME = "name";

    public static void writeOne(Animal animal) {
        try (var conn = Connector.getConnection();
             var pstmt = conn.prepareStatement(INSERT)) {
            pstmt.setString(1, animal.getType().getShortName());
            pstmt.setInt(2, animal.getAge());
            pstmt.setString(3, animal.getName());
            if (pstmt.executeUpdate() != 1) {
                throw new SQLException("didn't write in date base");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("database write error\n" + e.getMessage(), e);
        }
    }

    public static void writeAll(List<Animal> animalList) {
        try (var conn = Connector.getConnection();
             var pstmt = conn.prepareStatement(INSERT)) {
            int operationCount = 0;
            for (Animal animal : animalList) {
                pstmt.setString(1, animal.getType().getShortName());
                pstmt.setInt(2, animal.getAge());
                pstmt.setString(3, animal.getName());
                pstmt.addBatch();
                operationCount++;

                // Execute every 1000 items.
                if (operationCount % 1000 == 0 || operationCount == animalList.size()) {
                    checkBatchExecution(pstmt.executeBatch());
                }
            }
            System.out.println("данные записанны в таблицу");
        } catch (SQLException e) {
            throw new IllegalStateException("database write error\n" + e.getMessage(), e);
        }
    }

    static void checkBatchExecution(int[] batchResults) throws SQLException {
        if (batchResults == null) {
            System.out.println("Batch execution result is null.");
            return;
        }

        for (int batchResult : batchResults) {
            if (batchResult >= 0 || Statement.SUCCESS_NO_INFO == batchResult) {
                // everything is fine.
                continue;
            }

            var msg = new StringBuilder("Batch execution error:\nwhen executing the batch,");

            if (Statement.EXECUTE_FAILED == batchResult) {
                msg.append("result code 'EXECUTE_FAILED' was received.");
                throw new SQLException(msg.toString());
            }

            msg.append("Unknown result code ").append(batchResult).append(" was received");
            throw new SQLException(msg.toString());
        }
    }

    public static List<Animal> readAll() {
        try (var conn = Connector.getConnection();
             var stmt = conn.createStatement();
             var res = stmt.executeQuery(SELECT_ALL)) {
            var dataObjs = new ArrayList<Animal>();
            while (res.next()) {
                dataObjs.add(convertResultToDataObj(res));
            }
            return dataObjs;
        } catch (SQLException e) {
            throw new IllegalStateException("database read error\n" + e.getMessage(), e);
        }
    }

    private static Animal convertResultToDataObj(ResultSet res) throws SQLException {
        var type = res.getString(COL_TYPE);
        var animalType = AnimalType.getAnimalType(type);
        if (animalType == null) {
            throw new SQLException("Wrong type " + type);
        }
        return new Animal(res.getInt(COL_ID), animalType, res.getInt(COL_AGE), res.getString(COL_NAME));
    }

    public static void deleteAll() {
        try (var conn = Connector.getConnection();
             var pstmt = conn.prepareStatement(DELETE_ALL);) {
            pstmt.executeUpdate();
            System.out.println("данные таблицы удалены.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
