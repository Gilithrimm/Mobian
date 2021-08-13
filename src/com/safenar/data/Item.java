package com.safenar.data;

public class Item {
    private String name;
    private String desc;
    private String id;
    public static final Item NO_ITEM=new Item("missingno","",null);
    public static final Item[] BASE_INVENTORY=new Item[10];
    protected static Entity holder/*=Entity.BASIC_ENTITY*/;

    public Item(String name, String desc, String id) {
        this.name = name;
        this.desc = desc;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean exists(){
        return id != null;
    }

}
