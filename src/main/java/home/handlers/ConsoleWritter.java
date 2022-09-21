package home.handlers;

import java.util.List;

import home.model.Animal;

public final class ConsoleWritter implements IWritter {

    @Override
    public void write(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
