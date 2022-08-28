package home;

public class Main {

    public static void main(String[] args) {
        try {
            ArgsProcessor.execute(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
