package home.handlers;

import home.db.OperationsDB;
import home.model.Animal;

public final class DataBaseReader implements IReadder {

    @Override
    public void read() {
        for (Animal animal : OperationsDB.readAll()) {
            System.out.println(animal);
        }
    }
}
