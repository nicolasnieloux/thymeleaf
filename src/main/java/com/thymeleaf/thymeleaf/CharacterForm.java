package com.thymeleaf.thymeleaf;

public class CharacterForm {
    private String name;
    private Type type;
    private int id;

    private int lifePoint;

    public CharacterForm() {
    }

    public CharacterForm(String name, Type type, int lifePoint) {
        this.name = name;
        this.type = type;
        this.lifePoint = lifePoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
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
}
