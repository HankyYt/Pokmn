package ru.mirea.pkmn;

import java.io.Serializable;

public class AttackSkill implements Serializable {
    private String name, description, cost;
    private int damage;
    public static final long serialVersionUID = 1L;

    public AttackSkill(String name, String description, String cost, int damage) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.damage = damage;
    }

    public AttackSkill()
    {}

    @Override
    public String toString() {
        return "\nAttackSkill\n" +
                "name='" + name + '\'' + "\n" +
                "description='" + description + '\'' + "\n" +
                "cost='" + cost + '\'' + "\n" +
                "damage=" + damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
