package com.example.bataillenavale;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bateau {
    private int taille_x;
    private int taille_y;
    private int x;
    private int y;
    private int image;

    public Bateau(int x, int y, int taille_x, int taille_y, int image) {
        this.x = x;
        this.y = y;
        this.taille_x = taille_x;
        this.taille_y = taille_y;
        this.image = image;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTaille_x() {
        return taille_x;
    }

    public void setTaille_x(int taille_x) {
        this.taille_x = taille_x;
    }

    public int getTaille_y() {
        return taille_y;
    }

    public void setTaille_y(int taille_y) {
        this.taille_y = taille_y;
    }

    public int getImage() {
        return image;
    }
}
