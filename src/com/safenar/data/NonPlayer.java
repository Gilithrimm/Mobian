package com.safenar.data;

import com.safenar.Race;

public class NonPlayer extends Entity{

    public NonPlayer(Race race, long hp, int attack, int defense, int speed, int luck, Item mainHand, Item[] inventory, String name) {
        super(name, race, hp, attack, defense, speed, luck, mainHand, inventory);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
