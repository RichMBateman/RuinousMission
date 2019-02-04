package com.bateman.richard.model;

import java.util.ArrayList;

/**
 * Models a participant of combat (either for the player or the computer).  Always based off a CombatantReference.
 */
public class Combatant {
    public String name;
    public String preferredZone;
    public int hp, sp, speed, morale, stealth;
    private Deck m_deck = new Deck();

    public Combatant(CombatantReference reference) {
        name = reference.name;
        preferredZone = reference.preferredZone;
        hp = reference.hp;
        sp = reference.sp;
        speed = reference.speed;
        morale = reference.morale;
        stealth = reference.stealth;
    }

    public void addActionCard(String key, int quantity) {
        while(quantity > 0) {
            m_deck.m_keys.add(key);
            quantity--;
        }
    }

    @Override
    public String toString() {
        return "Combatant " + name + "\r\n"
                + "HP: " + hp + "\r\n"
                + "SP: " + sp + "\r\n"
                + "speed: " + speed + "\r\n"
                + "morale: " + morale + "\r\n";
    }

    private static class Deck {
        private ArrayList<String> m_keys = new ArrayList<>();

    }
}
