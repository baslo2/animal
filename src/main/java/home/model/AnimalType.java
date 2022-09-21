package home.model;

public enum AnimalType {

    DOG("dog"),
    CAT("cat"),
    MOUSE("mouse");

    private final String shortName;

    AnimalType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static AnimalType getAnimalType(String type) {
        for (AnimalType animalType : AnimalType.values()) {
            if (type.strip().equalsIgnoreCase(animalType.getShortName())) {
                return animalType;
            }
        }
        return null;
    }
}
