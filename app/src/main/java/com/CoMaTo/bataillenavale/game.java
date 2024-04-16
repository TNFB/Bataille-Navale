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
import java.util.Arrays;

public class game extends AppCompatActivity {
    private GameView gameView;
    private static boolean turn = true;
    private int[][] my_grid = new int[10][10];

    private int[][] ia_grid = new int[10][10];

    private DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.GameView);

        dataManager = new DataManager(this);
        dataManager.saveFlotte(dataManager.Flotte1(), "flotte1");
        gameView.setBateaux(dataManager.getFlotte("flotte1"));
        initGrid(my_grid, true);
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
                System.out.println(Arrays.toString(boatMatrix[i]));
                for (int j = 0; j < boatMatrix[i].length; j++) {
                        grid[startX + j][startY + i] = boatMatrix[i][j];
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

    private void updateGrid(int[][] grid,boolean turn){
    }
}
