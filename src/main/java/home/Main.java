package home;

import home.processing.ArgsProcessor;
import home.utils.Utils;

public final class Main {

    public static void main(String[] args) {
        try {
             Utils.measureSpendTime(new ArgsProcessor()::executeProcess, args);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}