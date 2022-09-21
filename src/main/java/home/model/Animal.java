package home.model;

import java.util.Objects;

public final class Animal {

    private final int id;
    private final AnimalType type;
    private final int age;
    private final String name;

    public Animal(AnimalType type, int age, String name) {
        this(0, type, age, name);
    }

    public Animal(int id, AnimalType type, int age, String name) {
        this.id = id;
        this.type = type;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public AnimalType getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getAge(), getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Animal)) {
            return false;
        }

        Animal other = (Animal) obj;
        return type == other.getType()
                && age == other.getAge()
                && Objects.equals(name, other.getName());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        if (id != 0) {
            sb.append("id: ").append(id).append("; ");
        }
        return sb.append("type: ").append(type).append("; age: ").append(age).append("; name: ").append(
                name).toString();
    }
}
