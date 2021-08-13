package com.safenar;

public enum ArgType {
    INT(false),
    CINT(true),//constant int
    FLOAT(false),
    CFLOAT(true),//const float
    TEXT(false),
    CTEXT(true),//const text||String
    BOOL(false),
    ARRAY(false),//list
    CARRAY(true),//array
    OBJECT(false),
    ;// TODO: 14.07.2021 objects
    private final boolean isConst;

    ArgType(boolean isConst) {
        this.isConst = isConst;
    }

    public boolean isConst() {
        return isConst;
    }


}
