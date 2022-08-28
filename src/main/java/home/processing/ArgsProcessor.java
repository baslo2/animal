package home.processing;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

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
        Writter writter = null;
        if (cliParams.getValueOfShow() != null) {
            params = cliParams.getValueOfShow();
            writter = new ConsoleWritter();
        } else if (cliParams.getValueOfCustom() != null) {
            params = cliParams.getValueOfCustom();
            writter = new CustomWritter();
        } else if (cliParams.getVaLueOfWriteDB() != null) {
            params = cliParams.getVaLueOfWriteDB();
            writter = new DataBaseWritter(cliParams.getValueOfReadDB());
        } else if (cliParams.getValueOfFile() != null) {
            params = cliParams.getValueOfFile();
            writter = new FileWritter();
        } else if (cliParams.getValueOfReadDB()) {
            writter = new DataBaseReader();
        } else if (cliParams.isClearDataBase()) {
            writter = new DataBaseClear();
        } else {
            jCommander.usage();
            System.exit(0);
        }

        writter.write(params);
    }
}
