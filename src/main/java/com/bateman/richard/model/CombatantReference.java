package com.bateman.richard.model;

/**
 * Represents a blueprint for a combatant.
 */
public class CombatantReference {
    public String name;
    public String preferredZone;
    public int hp, sp, speed, morale, stealth;

    private CombatantReference(Builder builder) {
        name = builder.name;
        preferredZone=builder.preferredZone;
        hp = builder.hp;
        sp = builder.sp;
        speed = builder.speed;
        morale = builder.morale;
        stealth=builder.stealth;
    }

    public static class Builder{
        private String name;
        private String preferredZone;
        private int hp, sp, speed, morale, stealth;

        public Builder() {}

        public CombatantReference build() {
            return new CombatantReference(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setPreferredZone(String preferredZone) {
            this.preferredZone = preferredZone;
            return this;
        }
        public Builder setHp(int hp) {
            this.hp = hp;
            return this;
        }
        public Builder setSp(int sp) {
            this.sp = sp;
            return this;
        }
        public Builder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }
        public Builder setMorale(int morale) {
            this.morale = morale;
            return this;
        }
        public Builder setStealth(int stealth) {
            this.stealth = stealth;
            return this;
        }
    }
}
