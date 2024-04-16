package com.CoMaTo.bataillenavale;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class game extends AppCompatActivity {
    private GameView gameView;
    private static boolean turn = true;
    public static int[][] my_grid = new int[10][10];

    public static int[][] ia_grid = new int[10][10];

    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.GameView);
        dataManager = new DataManager(this);
        dataManager.saveFlotte(dataManager.Flotte1(), "flotte1");
        dataManager.saveFlotte(dataManager.Flotte2(), "flotte2");
        gameView.setBateaux(dataManager.getFlotte("flotte1"));
        initGrid(my_grid, true);
        initGrid(ia_grid, false);
        displaygrid();
    }

    public static void setTurn(boolean turn){
        game.turn = turn;
    }

    public static boolean getTurn(){
        return turn;
    }

    private void initGrid(int[][] grid, boolean turn) {
        Bateau[] flotte;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = 0;
            }
        }
        if(turn){
            flotte = dataManager.getFlotte("flotte1");
        } else {
             flotte = dataManager.getFlotte("flotte2");
        }
        for (Bateau boat : flotte) {
            int[][] boatMatrix = boat.getMatrice();
            int startX = boat.getX();
            int startY = boat.getY();

            for (int i = 0; i < boatMatrix.length; i++) {
                for (int j = 0; j < boatMatrix[i].length; j++) {
                    if (startY + i >= 0 && startY + i < 10 && startX + j >= 0 && startX + j < 10) {
                        grid[startY + i][startX + j] = boatMatrix[i][j];
                    }
                }
            }
        }

    }

    private void displaygrid(){
        //affichage de my grid et de ia grid l'une à côté de l'autre
        System.out.println("My grid:               IA grid:");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(my_grid[i][j] + " ");
            }
            System.out.print("   ");
            for(int j = 0; j < 10; j++){
                System.out.print(ia_grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static Hit play(int[][] grid,int x, int y){
        switch(grid[y][x]){
            case 0:
                grid[y][x] = 2;
                return new Hit(x, y, 2);
            case 1:
                grid[y][x] = 3;
                return new Hit(x, y, 3);
            default:
                return null;
        }
    }
}
