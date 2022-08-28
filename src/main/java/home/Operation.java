package home;

enum Operation {

    S("-s", "-show"),
    F("-f", "-file"),
    RD("-rd", "-read-db"),
    WD("-wd", "-write-db"),
    C("-c", "-custom");

    private final String shortName;
    private final String longName;

    Operation(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    String getShortName() {
        return shortName;
    }

    String getLongName() {
        return longName;
    }

    static Operation getOperation(String operName) {
        for (Operation oper : Operation.values()) {
            String name = operName.strip();
            if (name.equalsIgnoreCase(oper.getShortName())
                    || name.equalsIgnoreCase(oper.getLongName())) {
                return oper;
            }
        }
        return null;
    }
}
