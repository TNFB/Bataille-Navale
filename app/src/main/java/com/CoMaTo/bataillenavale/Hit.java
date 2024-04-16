package com.CoMaTo.bataillenavale;

public class Hit {
    private int x;
    private int y;
    private int type;

    public Hit(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTypeHit() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTypeHit(int type) {
        this.type = type;
    }
}
