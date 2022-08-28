package home.processing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import home.db.OperationsDB;

public final class DataBaseWritter implements Writter {

    private final boolean needRead;

    public DataBaseWritter(boolean needRead) {
        this.needRead = needRead;
    }

    @Override
    public void write(String parametrs) {
        List<String> paramList = Arrays.asList(parametrs.split(","));
        if (paramList.size() > 3) {
            OperationsDB.writeAll(paramList);
            System.out.println("write batch in DB was correct");
        } else {
            for (String param : new HashSet<>(paramList)) {
                OperationsDB.writeOne(param);
            }
            System.out.println("write in DB was correct");
        }
        if (needRead) {
            read();
        }
    }

    private void read() {
        for (String param : OperationsDB.readAll()) {
            System.out.println(param);
        }
    }
}
