package home.processing;

import java.util.List;

import home.db.OperationsDB;
import home.model.Animal;

public final class DataBaseReader implements Writter {

    @Override
    public void write(List<Animal> animals) {
        for (Animal animal : OperationsDB.readAll()) {
            System.out.println(animal);
        }
    }
}
