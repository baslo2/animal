package home;

import home.process.ArgsProcessor;
import home.utils.Utils;

public final class Main {

    public static void main(String[] args) {
        try {
            Settings.readSettings();

            var argsProcessor = new ArgsProcessor();
            if (Settings.hasMeasure()){
                Utils.measureSpendTime(argsProcessor::executeProcess, args);
            } else {
                argsProcessor.executeProcess(args);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}