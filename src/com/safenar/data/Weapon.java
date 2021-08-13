package com.safenar.data;

import java.util.HashMap;

public class Weapon extends Item{
    private final HashMap<String,Integer> stats=new HashMap<>();

    public Weapon(String name, String desc, String id,Maps... stats) {
        super(name, desc, id);
        for (Maps kv:stats) {
            this.stats.put(kv.key(),kv.value());
        }
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Maps kv) {
        Integer put = stats.put(kv.key(),kv.value());
    }
}
