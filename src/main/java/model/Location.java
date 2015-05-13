package model;

public class Location {

    public final String id;
    public final String name;

    public Location(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
