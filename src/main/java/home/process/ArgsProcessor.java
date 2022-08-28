package home.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import home.handlers.ConsoleWritter;
import home.handlers.CustomWritter;
import home.handlers.DataBaseClear;
import home.handlers.DataBaseReader;
import home.handlers.DataBaseWritter;
import home.handlers.FileWritter;
import home.handlers.ICleaner;
import home.handlers.IHandler;
import home.handlers.IReadder;
import home.handlers.IWritter;
import home.model.Animal;
import home.model.AnimalType;

public final class ArgsProcessor {

    private final CliParams cliParams = new CliParams();
    private JCommander jCommander;

    public void executeProcess(String[] args) {
        handleInputArgs(args);
        run();
    }

    private void handleInputArgs(String[] args) {
        jCommander = JCommander.newBuilder()
            .addObject(cliParams)
            .programName("simpleCLI")
            .build();

        try {
            jCommander.parse(args);
        } catch (ParameterException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void run() {
        if (cliParams.isHelp()) {
            jCommander.usage();
            System.exit(0);
        }

        String params = null;
        IHandler handler = null;
        if (cliParams.getValueOfShow() != null) {
            params = cliParams.getValueOfShow();
            handler = new ConsoleWritter();
        } else if (cliParams.isCustom()) {
            handler = new CustomWritter();
        } else if (cliParams.getVaLueOfWriteDB() != null) {
            params = cliParams.getVaLueOfWriteDB();
            handler = new DataBaseWritter(cliParams.isReadDB());
        } else if (cliParams.getValueOfFile() != null) {
            params = cliParams.getValueOfFile();
            handler = new FileWritter();
        } else if (cliParams.isReadDB()) {
            handler = new DataBaseReader();
        } else if (cliParams.isClearDataBase()) {
            handler = new DataBaseClear();
        } else {
            jCommander.usage();
            System.exit(0);
        }

        startProcessing(handler, params);
    }

    private void startProcessing(IHandler handler, String params) {
        if (handler instanceof IWritter) {
            ((IWritter) handler).write(convertToAnimalList(params));
        } else if (handler instanceof IReadder) {
            ((IReadder) handler).read();
        } else if (handler instanceof ICleaner) {
            ((ICleaner) handler).clean();
        }
    }

    private List<Animal> convertToAnimalList(String params) {
        var animals = new ArrayList<Animal>();

        if (params == null) {
            return animals;
        }

        List<String> rowAnimals = Arrays.asList(params.split(","));
        for (String s : rowAnimals) {
            List<String> paramsOfAnimal = Arrays.asList(s.split("_"));
            checkParamsCount(paramsOfAnimal);
            animals.add(new Animal(getType(paramsOfAnimal.get(0)), getAge(paramsOfAnimal.get(1)),
                paramsOfAnimal.get(2)));
        }
        return animals;
    }

    private void checkParamsCount(List<String> paramsOfAnimal) {
        if (paramsOfAnimal.size() != 3) {
            throw new IllegalStateException("Incorect value of arguments for model Animal");
        }
    }

    private AnimalType getType(String typeParam) {
        var type = AnimalType.getAnimalType(typeParam);
        if (type != null) {
            return type;
        }
        throw new IllegalStateException("Incorrect AnimalType: " + typeParam);
    }

    private int getAge(String ageParam) {
        try {
            return Integer.parseInt(ageParam);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(ageParam + " it's not a number.");
        }
    }
}
