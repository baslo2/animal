package home.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import home.model.Animal;

public final class FileWritter implements IWritter {

    private static final int INDENT = 4;
    private static final int INDICATOR_INDENT = 2;

    private static final File FILE = new File("listAnimals.yaml");

    @Override
    public void write(List<Animal> animals) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE.toPath(), StandardCharsets.UTF_8);) {
            writer.write(exportAllAnimalsToString(animals));
            writer.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while write to " + FILE.getName() + ".", e);
        }
    }

    private String exportAllAnimalsToString(List<Animal> animals) {
        var convertedAnimals = new ArrayList<Map<String, String>>();
        for (Animal animal : animals) {
            convertedAnimals.add(convertAnimalToMap(animal));
        }

        var animalMap = new HashMap<String, Object>();
        animalMap.put(Tag.ANIMALS.getTagName(), convertedAnimals);

        var options = new DumperOptions();
        options.setIndent(INDENT);
        options.setIndicatorIndent(INDICATOR_INDENT);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        String animalsInYamlFormat = removeQuotersInValues(new Yaml(options).dump(animalMap));
        return prettyPrintDashOffsets(animalsInYamlFormat);
    }

    private Map<String, String> convertAnimalToMap(Animal animal) {
        var map = new LinkedHashMap<String, String>();
        map.put(Tag.TYPE.getTagName(), animal.getType().getShortName());
        map.put(Tag.AGE.getTagName(), String.valueOf(animal.getAge()));
        map.put(Tag.NAME.getTagName(), animal.getName());
        return map;
    }

    private String removeQuotersInValues(String string){
        return string.replace("'", "");
    }

    private String prettyPrintDashOffsets(String s){
        String base = " ".repeat(INDICATOR_INDENT) + "-";
        String pretty = base + "\n" + " ".repeat(INDENT - 1);
        return s.replace(base, pretty);
    }
}