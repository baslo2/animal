package home;

import java.sql.SQLException;

import home.processing.ConsoleWritter;
import home.processing.CustomWritter;
import home.processing.DataBaseReader;
import home.processing.DataBaseWritter;
import home.processing.FileWritter;
import home.processing.Writter;

class ArgsProcessor {

    static void execute(String[] args) throws SQLException {
        checkArgs(args);

        String operationName = args[0];
        String parametrs;
        if (args.length != 1) {
            parametrs = args[1];
        } else {
            parametrs = null;
        }

        Operation operation = Operation.getOperation(operationName);

        verifyOperation(operation, operationName);

        Writter writter = null;
        switch (operation) {
            case S:
                writter = new ConsoleWritter();
                break;
            case F:
                writter = new FileWritter();
                break;
            case RD:
                writter = new DataBaseReader();
                break;
            case WD:
                writter = new DataBaseWritter();
                break;
            case C:
                writter = new CustomWritter();
                break;
        }
        writter.write(parametrs);
    }

    private static void checkArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalStateException("Enter arguments");
        } else if (args.length > 2) {
            throw new IllegalStateException("Incorrect arguments count");
        }
    }

    private static void verifyOperation(Operation operation, String operationName) {
        if (operation == null) {
            throw new IllegalStateException("Unsupported operation: " + operationName);
        }
    }
}
