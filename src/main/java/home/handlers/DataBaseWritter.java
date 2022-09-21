package home.handlers;

import java.util.HashSet;
import java.util.List;

import home.db.OperationsDB;
import home.model.Animal;

public final class DataBaseWritter implements IWritter {

    private final boolean needRead;

    public DataBaseWritter(boolean needRead) {
        this.needRead = needRead;
    }

    @Override
    public void write(List<Animal> animals) {
        if (animals.size() > 3) {
            OperationsDB.writeAll(animals);
            System.out.println("write batch in DB was correct");
        } else {
            for (Animal animal : new HashSet<>(animals)) {
                OperationsDB.writeOne(animal);
            }
            System.out.println("write in DB was correct");
        }
        if (needRead) {
            read();
        }
    }

    private void read() {
        for (Animal animal : OperationsDB.readAll()) {
            System.out.println(animal);
        }
    }
}
