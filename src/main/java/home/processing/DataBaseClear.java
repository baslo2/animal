package home.processing;


import java.util.List;

import home.db.OperationsDB;
import home.model.Animal;

public class DataBaseClear implements Writter {


    @Override
    public void write(List<Animal> animals) {
        OperationsDB.deleteAll();
    }
}
