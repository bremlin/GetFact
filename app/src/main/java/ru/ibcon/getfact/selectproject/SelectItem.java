package ru.ibcon.getfact.selectproject;

import ru.ibcon.getfact.objects.SFEPS;

public class SelectItem {
    private String name;
    private String number;
    private int id;
    private int parentId;
    private int type;

    public static int EPS = 0;
    public static int PROJECT = 1;

    public SelectItem(String name, String number, int id, int parentId, int type) {
        this.name = name;
        this.number = number;
        this.id = id;
        this.parentId = parentId;
        this.type = type;
    }

    public SelectItem(SFEPS sfeps) {
        this.name = sfeps.getName();
        this.number = sfeps.getCode();
        this.id = sfeps.getId();
        this.parentId = sfeps.getParentId();
        this.type = EPS;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " -/- " + name;
    }
}
