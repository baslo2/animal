package home;

import home.processing.ArgsProcessor;
import home.utils.Utils;

public final class Main {

    public static void main(String[] args) {
        Utils.measureSpendTime(new ArgsProcessor()::executeProcess, args);
    }
}