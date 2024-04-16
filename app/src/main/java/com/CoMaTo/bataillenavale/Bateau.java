package com.CoMaTo.bataillenavale;


import java.io.Serializable;

public class Bateau implements Serializable {
    private int taille_x;
    private int taille_y;
    private int x;
    private int y;
    private int image;
    private int[][] matrice = new int[taille_x][taille_y];

    public Bateau(int x, int y, int taille_x, int taille_y, int image, int[][] matrice) {
        this.x = x;
        this.y = y;
        this.taille_x = taille_x;
        this.taille_y = taille_y;
        this.image = image;
        this.matrice = matrice;
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

    public int[][] getMatrice() {
        return matrice;
    }

    public static int[][] getMatriceBoat(int i) {
        switch (i) {
            case 1:
                return new int[][]{{1, 1}};
            case 2:
                return new int[][]{{1, 0}, {1, 1}, {1, 1}, {0, 1}};
            case 3:
                return new int[][]{{1}, {1}, {1}};
            case 4:
                return new int[][]{{1, 0}, {1, 1}, {1, 0}};
            case 5:
                return new int[][]{{1}, {1}, {1}, {1}};
            case 6:
                return new int[][]{{1}, {1}, {1}, {1}, {1}};
            default:
                break;
        } return new int[][]{{0}};
    }
}
