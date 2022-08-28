package home.processing;

public class ConsoleWritter implements Writter {

    @Override
    public void write(String parametrs) {
        for (String param : parametrs.split(",")) {
            System.out.println(param);
        }
    }
}
