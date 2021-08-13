package com.safenar.data;

import com.safenar.Race;

public class Player extends Entity{
    private int posX;
    private int posY;

    public Player(String name, Race race, long hp, int attack, int defense, int speed, int luck, Item mainHand, Item[] inventory) {
        super(name, race, hp, attack, defense, speed, luck, mainHand, inventory);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getName() {
        return name;
    }
}
