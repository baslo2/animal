package home.processing;

import home.db.OperationsDB;

public class DataBaseReader implements Writter {

    @Override
    public void write(String parametrs) {
        for (String param : OperationsDB.readAll()) {
            System.out.println(param);
        }
    }
}
