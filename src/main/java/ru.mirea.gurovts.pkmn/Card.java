package ru.mirea.gurovts.pkmn;

import java.util.List;
import java.util.ArrayList;

public class Card {

    private String name;
    private EnergyType pokemonType, weaknessType, resistanceType;
    private PokemonStage pokemonStage;
    private int hp;
    private Card evolvesFrom;
    private List<AttackSkill> skill;
    private String retreatCost, gameSet;
    private char regulationMark;
    private Student pokemonOwner;

    public Card()
    {}

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", pokemonType=" + pokemonType +
                ", weaknessType=" + weaknessType +
                ", resistanceType=" + resistanceType +
                ", pokemonStage=" + pokemonStage +
                ", hp=" + hp +
                ", evolvesFrom=" + evolvesFrom +
                ", skill=" + skill +
                ", retreatCost='" + retreatCost + '\'' +
                ", gameSet='" + gameSet + '\'' +
                ", regulationMark=" + regulationMark +
                ", pokemonOwner=" + pokemonOwner +
                '}';
    }

    public Card(String name, EnergyType pokemonType, EnergyType weaknessType, EnergyType resistanceType, PokemonStage pokemonStage, int hp, Card evolvesFrom, List<AttackSkill> skill, String retreatCost, String gameSet, char regulationMark, Student pokemonOwner) {
        this.name = name;
        this.pokemonType = pokemonType;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.pokemonStage = pokemonStage;
        this.hp = hp;
        this.evolvesFrom = evolvesFrom;
        this.skill = skill;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.regulationMark = regulationMark;
        this.pokemonOwner = pokemonOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnergyType getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(EnergyType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public EnergyType getWeaknessType() {
        return weaknessType;
    }

    public void setWeaknessType(EnergyType weaknessType) {
        this.weaknessType = weaknessType;
    }

    public EnergyType getResistanceType() {
        return resistanceType;
    }

    public void setResistanceType(EnergyType resistanceType) {
        this.resistanceType = resistanceType;
    }

    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }

    public void setPokemonStage(PokemonStage pokemonStage) {
        this.pokemonStage = pokemonStage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Card getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(Card evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public List<AttackSkill> getSkill() {
        return skill;
    }

    public void setSkill(List<AttackSkill> skill) {
        this.skill = skill;
    }

    public String getRetreatCost() {
        return retreatCost;
    }

    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }

    public String getGameSet() {
        return gameSet;
    }

    public void setGameSet(String gameSet) {
        this.gameSet = gameSet;
    }

    public char getRegulationMark() {
        return regulationMark;
    }

    public void setRegulationMark(char regulationMark) {
        this.regulationMark = regulationMark;
    }

    public Student getPokemonOwner() {
        return pokemonOwner;
    }

    public void setPokemonOwner(Student pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }
}
