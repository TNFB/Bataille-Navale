package com.CoMaTo.bataillenavale;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.app.Service;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.Provider;

public class game extends AppCompatActivity {
    private GameView gameView;
    private static boolean turn = true;
    public static int[][] my_grid = new int[10][10];

    public static int[][] ia_grid = new int[10][10];

    public static int ia_score = 0;
    public static int my_score = 0;

    private String flotte_choice;

    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameView = findViewById(R.id.GameView);

        Intent intent = new Intent(this, CheckWin.class);
        startService(intent);

        dataManager = new DataManager(this);
        dataManager.initFlotte();

        flotte_choice = "flotte1";
        gameView.setBateaux(dataManager.getFlotte(flotte_choice));
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
            flotte = dataManager.getFlotte(flotte_choice);
        } else {
            int random = (int)(Math.random() * 4 + 1);
            System.out.println(random);
            switch(random){
                case 1:
                    flotte = dataManager.getFlotte("flotte1");
                    break;
                case 2:
                    flotte = dataManager.getFlotte("flotte2");
                    break;
                case 3:
                    flotte = dataManager.getFlotte("flotte3");
                    break;
                case 4:
                    flotte = dataManager.getFlotte("flotte4");
                    break;
                default:
                    flotte = dataManager.getFlotte("flotte1");
                    break;
            }
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
                return new Hit(x, y, 0);
            case 1:
                grid[y][x] = 3;
                return new Hit(x, y, 1);
            default:
                return null;
        }
    }


    public void checkEndGame(int win) {
        if (win == 1 || win == 2) {
            my_score = 0;
            ia_score = 0;
            // Arrêter le service CheckWin
            Intent intent = new Intent(this, CheckWin.class);
            stopService(intent);

            // Redirection vers la page de fin de partie
            intent = new Intent(this, EndGame.class);
            intent.putExtra("gagnant", win);
            startActivity(intent);
        }
    }
}
