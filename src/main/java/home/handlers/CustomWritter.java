package home.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import home.model.Animal;
import home.model.AnimalType;

public final class CustomWritter implements IWritter {

    private static final String STOP = "n";

    @Override
    public void write(List<Animal> animals) {
        var paramList = new ArrayList<Animal>();

        try (var sc = new Scanner(System.in)) {
            System.out.println("Applycation started.");
            boolean isStopped = false;
            while (!isStopped) {
                System.out.println("Enter type:");
                AnimalType type = AnimalType.getAnimalType(sc.next());

                System.out.println("Enter age:");
                int age = sc.nextInt();

                System.out.println("Enter name:");
                String name = sc.next();

                Animal animal = new Animal(type, age, name);
                paramList.add(animal);
                System.out.println("animal added: " + animal);
                System.out.println("Total animals added: " + paramList.size());

                System.out.println("Do you want add one more? (y/n)");
                isStopped = STOP.equalsIgnoreCase(sc.next());
            }
        }
        for (Animal animal : paramList) {
            System.out.println("!!! " + animal);
        }
        System.out.println("Applycation finished");
    }
}
