package com.thymeleaf.thymeleaf;

public class Character {
    private int id;
    private String name;

    private Type type;

    private int lifePoint;

    public Character(int id, String name, Type type, int lifePoint) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lifePoint = lifePoint;
    }

    public Character(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Character(String name, Type type, int lifePoint) {
        this.name = name;
        this.type = type;
        this.lifePoint = lifePoint;
    }

    public Character(String name) {
        this.name = name;
    }

    public Character() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }
}
