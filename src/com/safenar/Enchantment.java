package com.safenar;

public enum Enchantment {
    CURSE_OF_DURABILITY("curses"),
    CURSE_OF_("curses"),
    UNBREAKABLENESS("helpers");

    public final String enchantmentType;

    Enchantment(String enchantmentType) {
        this.enchantmentType = enchantmentType;
    }
}
