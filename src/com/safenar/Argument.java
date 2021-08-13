package com.safenar;

import java.util.List;

public class Argument<T> {

    private T type;
    private List<T> defaults;
    private boolean isRequired;

    public Argument() {}

    public Argument(List<T> defaults, boolean isRequired) {
        this.defaults = defaults;
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }


    public List<T> getDefaults() {
        return defaults;
    }

    public void setDefaults(List<T> defaults) {
        this.defaults = defaults;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Argument{" +
                ", defaults=" + defaults +
                ", isRequired=" + isRequired +
                '}';
    }


}