package com.CoMaTo.bataillenavale;

public class Hit {
    private final int x;
    private final int y;
    private final int type;

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
}
