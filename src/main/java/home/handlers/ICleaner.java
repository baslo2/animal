package home.handlers;

import java.util.List;

import home.model.Animal;

public interface ICleaner extends IHandler {

    @Override
    default void handle(List<Animal> animals) {
        clean();
    }

    void clean();
}
