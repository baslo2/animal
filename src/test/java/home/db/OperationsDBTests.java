package home.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

final class OperationsDBTests {

    @Test
    void checkBatchSuccesTest() {
        assertDoesNotThrow(() -> home.db.OperationsDB.checkBatchExecution(new int[] {-2,0,1,2,3,4}));
    }

    @Test
    void checkBatchFailTest() {
        assertThrows(SQLException.class, () -> home.db.OperationsDB.checkBatchExecution(new int[] {0,1,2,-3}));
    }

    @Test
    void checkBatchFailUnknownCode() {
        assertThrows(SQLException.class, () -> home.db.OperationsDB.checkBatchExecution(new int[] {0,2,-1,4}));
    }

    @Test
    void checkBatchNullTest() {
        assertDoesNotThrow(() -> home.db.OperationsDB.checkBatchExecution(null));
    }
}