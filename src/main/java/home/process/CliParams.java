package home.process;

import com.beust.jcommander.Parameter;

final class CliParams {

    @Parameter(names = {"--show", "-s"},
        description = "privet")
    private String valueOfShow;

    @Parameter(names = {"--file-export", "-fe"})
    private String valueOfFileExport;

    @Parameter(names = {"--file-import", "-fi"})
    private boolean isFileImport;

    @Parameter(names = {"--read-db", "-rd"})
    private boolean isReadDB;

    @Parameter(names = {"--write-db", "-wd"})
    private String valueOfWriteDB;

    @Parameter(names = {"--custom", "-c"})
    private boolean isCustom;

    @Parameter(names = {"--help", "-h"})
    private boolean help;

    @Parameter(names = {"--clear-db", "-cd"})
    private boolean isClearDataBase;

    public String getValueOfShow() {
        return valueOfShow;
    }

    public String getValueOfFileExport() {
        return valueOfFileExport;
    }

    public boolean isFileImport() {
        return isFileImport;
    }

    public boolean isReadDB() {
        return isReadDB;
    }

    public String getVaLueOfWriteDB() {
        return valueOfWriteDB;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isClearDataBase() {
        return isClearDataBase;
    }
}
