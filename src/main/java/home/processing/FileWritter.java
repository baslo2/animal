package home.processing;

import java.util.HashSet;
import java.util.List;

import home.model.Animal;

public final class FileWritter implements Writter {

    @Override
    public void write(List<Animal> animals) {
        for (Animal animal : new HashSet<>(animals)) {
            System.out.println(">>> " + animal);
        }
    }
}
