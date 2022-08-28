package home.processing;

import java.util.List;

import home.model.Animal;

public final class ConsoleWritter implements Writter {

    @Override
    public void write(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
