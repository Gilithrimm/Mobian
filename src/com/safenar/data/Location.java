package com.safenar.data;

import java.util.Objects;

public class Location{
    private String name;
    private String description;
    private String type;
    private String id;
//    Location previousLocation;


    public Location() {
    }

    public Location(String name, String description, String type, String id) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.id = id;
    }

    //    public Location(String name, String description, String type, Location previousLocation) {
//        this.name = name;
//        this.description = description;
//        this.type = type;
//        this.previousLocation = previousLocation;
//        posX=previousLocation.posX;
//        posY=previousLocation.posY;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(getName(), location.getName()) &&
                Objects.equals(getType(), location.getType()) &&
                getId().equals(location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getId());
    }
}
