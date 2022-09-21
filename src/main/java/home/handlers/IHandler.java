package home.handlers;

import java.util.List;

import home.model.Animal;

public interface IHandler {
    
    default void handle(List<Animal> animals) {
        // overwrite when need
    }
}
