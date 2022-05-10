package com.safenar.swing;

import java.util.Objects;

public class CmdOption {
    private final String name;
    private final String desc;

    public CmdOption(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CmdOption) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }


}
