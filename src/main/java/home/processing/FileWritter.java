package home.processing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FileWritter implements Writter {

    @Override
    public void write(String parametrs) {
        List<String> paramList = Arrays.asList(parametrs.split(","));
        for (String param : new HashSet<>(paramList)) {
            System.out.println(">>> " + param);
        }
    }
}
