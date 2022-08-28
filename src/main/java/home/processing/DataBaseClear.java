package home.processing;

import home.db.OperationsDB;

public class DataBaseClear implements Writter {


    @Override
    public void write(String parametrs) {
        OperationsDB.deleteAll();
    }
}
