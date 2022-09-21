package home.handlers;

import java.util.List;

import home.model.Animal;

public interface IReadder extends IHandler {

    @Override
    default void handle(List<Animal> animals) {
        read();
    }

    void read();
}
