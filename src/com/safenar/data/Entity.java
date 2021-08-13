package com.safenar.data;

import com.safenar.Race;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Entity implements IEntity{
    static Random rand=new Random();
    String name;
    Race race;
    public long dmg;
    public long critDmg;
    private long hp;
    private int attack;
    private int defense;
    private int speed;
    private int luck;
    private boolean hasDodged;
    private boolean critical;
    @Nullable
    public Item mainHand;
    @Nullable
    Item[] inventory;
    public static Entity BASIC_ENTITY=new Entity("Basic test entity", Race.NO_RACE,100,10,10,10,10,Item.NO_ITEM,new Item[10]);

    public Entity(String name, Race race, long hp, int attack, int defense, int speed, int luck, @Nullable Item mainHand, @Nullable Item[] inventory) {
        this.name = name;
        this.race = race;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.luck = luck;
        this.mainHand = mainHand;
        this.inventory = inventory;
        this.hasDodged=this.getSpeed()+this.getLuck()/2==rand.nextInt(this.getLuck()*2);
    }

    @Override
    public void addItem(Item item) {
        for (Item entry : inventory)
            if (entry == null) {
                entry = item;
                break;
            }
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public boolean hasDodged() {
        return hasDodged;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setDodge(boolean hasDodged) {
        this.hasDodged = hasDodged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", race=" + race +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ", luck=" + luck +
                ", mainHand=" + mainHand +
                ", inventory=" + Arrays.toString(inventory) +
                '}';
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity entity)) return false;
        return getHp() == entity.getHp() &&
                getAttack() == entity.getAttack() &&
                getDefense() == entity.getDefense() &&
                getSpeed() == entity.getSpeed() &&
                getLuck() == entity.getLuck() &&
                name.equals(entity.name) &&
                race == entity.race &&
                Objects.equals(mainHand, entity.mainHand) &&
                Arrays.equals(inventory, entity.inventory);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, race, getHp(), getAttack(), getDefense(), getSpeed(), getLuck(), mainHand);
        result = 31 * result + Arrays.hashCode(inventory);
        return result;
    }
}
