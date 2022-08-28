package home.handlers;

import java.util.List;

import home.model.Animal;

public interface IWritter extends IHandler {
    void write(List<Animal> animals);
}
