package home;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import home.db.OperationsDB;
import home.model.Animal;
import home.model.AnimalType;
import home.process.ArgsProcessor;

final class MainTest {

    private static final String SETTINGS_FILE_NAME = "settings.properties";

    @Test
    void checkWriteDBTest() throws Exception {
        Animal animal = new Animal(AnimalType.CAT, 2, "testCat");
        String arg = animal.getType().getShortName() + "_" + animal.getAge() + "_" + animal.getName();

       try {
           String [] args = {"-wd", arg};

           String path = Paths.get(getClass().getResource(SETTINGS_FILE_NAME).toURI()).toAbsolutePath().toString();
           Settings.readSettings(path);

           new ArgsProcessor().executeProcess(args);

           List<Animal> expected = List.of(animal);
           List<Animal> actual = OperationsDB.readAll();

           assertArrayEquals(expected.toArray(), actual.toArray());
       } finally {
           OperationsDB.deleteAll();
       }
    }
}
