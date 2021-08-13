package com.safenar.data;

import com.safenar.Race;

import java.util.Arrays;
import java.util.List;

public class Enemy extends Entity{
    Item item;
    List<Item> loot;

    public Enemy(String name, Race race, long hp, int attack, int defense, int speed, int luck, Item mainHand, Item[] inventory) {
        super(name, race, hp, attack, defense, speed, luck, mainHand, inventory);
        loot= Arrays.asList(inventory);
    }


    public void setItem(Item item) {
        this.item = item;
    }


    @Override
    public String toString() {
        return "Enemy{" +
                "item=" + item +
                ", loot=" + loot +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", mainHand=" + mainHand +
                ", inventory=" + Arrays.toString(inventory) +
                '}';
    }
}
