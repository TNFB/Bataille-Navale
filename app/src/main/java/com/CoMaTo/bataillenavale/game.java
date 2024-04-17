package com.CoMaTo.bataillenavale;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class game extends AppCompatActivity {
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
        GameView gameView = findViewById(R.id.GameView);


        Intent intent = new Intent(this, CheckWin.class);
        startService(intent);

        dataManager = new DataManager(this);

        flotte_choice = dataManager.getCurrentFlotte();
        gameView.setBateaux(dataManager.getFlotte(flotte_choice));
        initGrid(my_grid, true);
        initGrid(ia_grid, false);
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
            int random = (int)(Math.random() * 4 + 1);
            System.out.println(random);
            switch(random){
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
        } else {
            flotte = dataManager.getFlotte(flotte_choice);
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
}
