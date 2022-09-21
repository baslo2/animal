package home.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import home.model.Animal;
import home.model.AnimalType;

public final class FileReader implements IReadder {

    private static final File FILE = new File("listAnimals.yaml");

    @Override
    public void read() {
        for (Animal animal : importAnimalsFromYaml(FILE)) {
            System.out.println(animal);
        }
    }

    private List<Animal> importAnimalsFromYaml(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            var yaml = new Yaml();
            Map<String, Object> rowData = yaml.load(inputStream);
            List<Animal> animals = parse(rowData);
            return animals;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not find: " + FILE.getName());
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while read file: " + FILE.getName());
        }
    }

    private List<Animal> parse(Map<String, Object> rowData) {
        if (rowData.size() != 1) {
            throw new IllegalArgumentException("Incorrect count of root tags.");
        }

        Entry<String, Object> rootTagData = rowData.entrySet().iterator().next();
        String rootTagName = rootTagData.getKey();

        if(!Tag.ANIMALS.getTagName().equals(rootTagName)) {
            throw new IllegalArgumentException("Unknown root tag name : " + rootTagName);
        }

        var animals = new ArrayList<Animal>();

        Object rootTagValue = rootTagData.getValue();
        List<?> rawAnimalsList = castToList(rootTagValue, rootTagName);
        for (Object rawAnimal : rawAnimalsList) {
            Map<?,?> rawAnimalMap = castToMap(rawAnimal, rootTagName);
            Map<String, String> rawAnimalStringMap = convertToStringMap(rawAnimalMap);
            animals.add(convertToAnimal(rawAnimalStringMap));
        }

        return animals;
    }

    private List<?> castToList (Object obj, String tagName) {
        if (obj instanceof List<?> list) {
            return list;
        }
        throw new IllegalArgumentException("Error while pase value of [%s]".formatted(tagName));
    }

    private Map<?,?> castToMap(Object obj, String tagName) {
        if (obj instanceof Map<?,?> map) {
            return map;
        }
        throw new IllegalArgumentException("Error whileparse value of [%s]".formatted(tagName));
    }

    private Map<String, String> convertToStringMap(Map<?, ?> rawAnimalMap) {
        return rawAnimalMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> converToString(entry.getKey()),
                        entry -> converToString(entry.getValue())));
    }

    private String converToString(Object obj) {
        return obj instanceof String ? (String) obj : obj.toString();
    }

    private Animal convertToAnimal(Map<String, String> rawAnimalStringMap) {
        String type = removeRequiredParam(Tag.TYPE, rawAnimalStringMap);
        AnimalType animalType = AnimalType.getAnimalType(type);
        if (animalType == null){
            throw new IllegalArgumentException("Wrong animal type received : " + type);
        }

        int age = 0;
        String name = null;

        for (Entry<String, String> tadData : rawAnimalStringMap.entrySet()){
            String tagName = tadData.getKey();
            String tagValue = tadData.getValue();

            Tag tag = Tag.getTag(tagName);
            if (tag == null) {
                throw new IllegalArgumentException("Incorrect tag name : " + tagName);
            }
            switch (tag) {
                case AGE ->  age = Integer.parseInt(tagValue);
                case NAME -> name = tagValue;
            }
        }

        if (age <= 0 || name == null) {
            throw new IllegalArgumentException("Incorrect value.");
        }

        return new Animal(animalType, age, name);
    }

    private String removeRequiredParam(Tag tag, Map<String, String> map){
        String tagName = tag.getTagName();
        String tagValue = map.remove(tagName);
        if (tagValue != null) {
            return tagValue;
        }
        throw new IllegalArgumentException("There is no required tad : " + tagName);
    }
}
