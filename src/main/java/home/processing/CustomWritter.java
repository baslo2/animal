package home.processing;

import java.util.ArrayList;
import java.util.Scanner;

public final class CustomWritter implements Writter {

    private static final String STOP = "STOP";

    @Override
    public void write(String parametrs) {
        var paramList = new ArrayList<String>();

        try (var sc = new Scanner(System.in)) {
            System.out.println("To breake operation enter '" + STOP + "'.\nEnter name(s):\n");
            boolean isStopped = false;
            String textFromUser = null;
            while (!isStopped) {
                textFromUser = sc.next();
                isStopped = STOP.equalsIgnoreCase(textFromUser);
                if (!isStopped) {
                    paramList.add(textFromUser);
                }
            }
        }

        for (String param : paramList) {
            System.out.println("!!! " + param);
        }
    }
}
