package home.handlers;

import java.util.List;

import home.model.Animal;

public interface IWritter extends IHandler {
    
    @Override
    default void handle(List<Animal> animals) {
        write(animals);
    }

    void write(List<Animal> animals);
}
