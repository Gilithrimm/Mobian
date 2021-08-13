package com.safenar;

import com.safenar.data.*;

public class Methods {

    static Player player=Main.player;
    private static String print;
    public static void go(int posX, int posY){
        player.setPosX(posX);
        player.setPosY(posY);
    }
    public static void go(Directions direction){
        switch (direction){
            case EAST -> player.setPosX(player.getPosX()+1);
            case WEST -> player.setPosX(player.getPosX()-1);
            case NORTH -> player.setPosY(player.getPosY()+1);
            case SOUTH -> player.setPosY(player.getPosY()-1);
            case SOUTHEAST -> {
                player.setPosX(player.getPosX()+1);
                player.setPosY(player.getPosY()-1);
            }
            case SOUTHWEST -> {
                player.setPosX(player.getPosX()-1);
                player.setPosY(player.getPosY()-1);
            }
            case NORTHEAST-> {
                player.setPosX(player.getPosX() + 1);
                player.setPosY(player.getPosY() + 1);
            }
            case NORTHWEST -> {
                player.setPosX(player.getPosX()-1);
                player.setPosY(player.getPosY()+1);
            }
        }
    }
    public static void take(Item takenItem) throws BadDataException {
        if (takenItem.exists()) player.addItem(takenItem);
        else throw new BadDataException("There's no such item!");
        Main.println("Picked up "+takenItem.getName());
    }
    static public String getPrint() {
        return print;
    }
    static public void setPrint(String print) {
        Methods.print = print;
    }
    public static void attack(Entity target, Entity attacker){
        attacker.dmg= attacker.getAttack()- target.getDefense();
        attacker.critDmg=attacker.dmg*3;
        target.setDodge(target.getSpeed()+target.getLuck()/2<Main.rand.nextInt(target.getLuck()*2));
        if (!target.hasDodged()){
            if (target.getDefense()<attacker.getAttack()||target.getDefense()<attacker.critDmg){
                if (target.isCritical()){
                    target.setHp(target.getHp()-attacker.critDmg);
                    setPrint(attacker.getName()+ " hit " + target.getName() +/*" using "+attacker.mainHand+*/", dealing "+ attacker.critDmg+ " damage!");
                }else {
                    target.setHp(target.getHp()-attacker.dmg);
                    setPrint(attacker.getName() + " hit " + target.getName() +/*" using "+attacker.mainHand+*/", dealing "+ attacker.dmg+ " damage!");
                }
            }else {
                setPrint(target.getName() +" has blocked "+ attacker.getName() +"'s attack!");
            }
        }else {
            setPrint(target.getName() +" has dodged "+ attacker.getName() +"'s attack!");
        }
        Main.println(getPrint());
    }
    public static void help() {
        for (Keyword keyword:Main.keywords) {
            Main.println(keyword.getName()+"-"+keyword.getDescription());
        }
    }
}
