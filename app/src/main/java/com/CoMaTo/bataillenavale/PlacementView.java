package com.CoMaTo.bataillenavale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class PlacementView extends View {
    private Bitmap backBitmap;
    private static String[] LETTER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[] NUMBER = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private int startX, startY, cellSize;
    private int offsetX, offsetY;
    private boolean isDragging = false;
    private static final int GRID_SIZE = 10;
    private int grid_width;
    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private Bateau[] flotte = new Bateau[6];
    private Bitmap[] bateaux = new Bitmap[6];
    private int currentBoat = -1;
    public PlacementView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //initialisation des variables selon la taille de l'écran du téléphone
        int phoneWidth = context.getResources().getDisplayMetrics().widthPixels;
        grid_width = (int) (phoneWidth * 0.88);
        cellSize = grid_width / GRID_SIZE;
        startX = (int) (grid_width * 0.06);
        startY = (int) (grid_width * 0.05);

        backBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_grid);
        backBitmap = Bitmap.createScaledBitmap(backBitmap, grid_width, grid_width, false);

        //ajout des bateaux à la flotte
        flotte[0] = new Bateau(startX, startY, 1, 4,R.drawable.bateau_4);
        flotte[1] = new Bateau(startX + cellSize, startY, 1, 4,R.drawable.bateau_4);
        flotte[2] = new Bateau(startX + cellSize * 2, startY, 1, 4,R.drawable.bateau_4);
        flotte[3] = new Bateau(startX + cellSize * 3, startY, 1, 4,R.drawable.bateau_4);
        flotte[4] = new Bateau(startX + cellSize * 4, startY, 1, 4,R.drawable.bateau_4);
        flotte[5] = new Bateau(startX + cellSize * 5, startY, 1, 4,R.drawable.bateau_4);

        //chargement des images
        for(int i = 0; i < 6; i++){
            bateaux[i] = BitmapFactory.decodeResource(getResources(), flotte[i].getImage());
            bateaux[i] = Bitmap.createScaledBitmap(bateaux[i], flotte[i].getTaille_x() * cellSize - 10, flotte[i].getTaille_y() * cellSize - 10, false);
        }
        initGrid();
    }

    private void initGrid(){
        grid = new int[GRID_SIZE][GRID_SIZE];
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        drawGrid(canvas, cellSize, startX, startY);
        drawText(canvas, cellSize, startX, startY);
        canvas.drawBitmap(backBitmap, startX, startY, new Paint());

        for(int i = 0; i < 6; i++){
            canvas.drawBitmap(bateaux[i], flotte[i].getX(), flotte[i].getY(), new Paint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(!isDragging) {
            currentBoat = whatboat((int) event.getX(), (int) event.getY());
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (currentBoat != -1){
                    isDragging = true;
                    offsetY = (int) event.getY() - flotte[currentBoat].getY();
                    offsetX = (int) event.getX() - flotte[currentBoat].getX();
                    System.out.println(offsetX);
                    System.out.println(offsetY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging && currentBoat != -1) {
                    int currentX = flotte[currentBoat].getX();
                    int currentY = flotte[currentBoat].getY();
                    flotte[currentBoat].setX((int) event.getX() - offsetX);
                    flotte[currentBoat].setY((int) event.getY() - offsetY);
                    isInside(currentBoat);
                    checkBoatPosition(currentBoat);
                    alreadyPlaced(currentX, currentY, currentBoat);
                    invalidate();  // Redessine le Canvas
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }

    private void drawText(Canvas canvas, int cellSize, int start_x, int start_y){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        for (int i = 0; i < GRID_SIZE; i++) {
            canvas.drawText(LETTER[i], start_x + (i * cellSize) + 32, start_y - 20, paint);
            canvas.drawText(NUMBER[i], start_x - 55, start_y + (i * cellSize) + 62, paint);
        }
    }
    private void drawGrid(Canvas canvas, int cellSize, int start_x, int start_y){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = start_x + (i * cellSize);
            canvas.drawLine(x, start_y, x, start_y + grid_width, paint);
        }
        for (int j = 0; j <= GRID_SIZE; j++) {
            int y = start_y + (j * cellSize);
            canvas.drawLine(start_x, y, start_x + grid_width, y, paint);
        }
    }
    private void checkBoatPosition(int currentboat){
        int x = flotte[currentboat].getX();
        int y = flotte[currentboat].getY();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (x >= startX + (i * cellSize) && x <= startX + ((i + 1) * cellSize) && y >= startY + (j * cellSize) && y <= startY + ((j + 1) * cellSize)) {
                    flotte[currentboat].setX(startX + (i * cellSize) + 10);
                    flotte[currentboat].setY(startY + (j * cellSize));
                }
            }
        }
    }

    private void isInside(int currentboat){
        int x = flotte[currentboat].getX();
        int y = flotte[currentboat].getY();
        int taille_x = flotte[currentboat].getTaille_x() * cellSize;
        int taille_y = flotte[currentboat].getTaille_y() * cellSize;
        if(x < startX){
            flotte[currentboat].setX(startX);
        }
        if(x + taille_x > grid_width){
            flotte[currentboat].setX(grid_width - taille_x + startX);

        }
        if(y < startY){
            flotte[currentboat].setY(startY);
        }
        if(y + taille_y > grid_width){
            flotte[currentboat].setY(grid_width - taille_y + startY);
        }
    }

    private int whatboat(int x, int y){
        for(int i = 0; i < 6; i++){
            if(x >= flotte[i].getX() && x <= flotte[i].getX() + flotte[i].getTaille_x() * (grid_width / GRID_SIZE) - 10 && y >= flotte[i].getY() && y <= flotte[i].getY() + flotte[i].getTaille_y() * (grid_width / GRID_SIZE) - 10){
                return i;
            }
        }
        return -1;
    }

    private void alreadyPlaced(int currentX, int currentY, int currentboat) {
        int x = flotte[currentboat].getX();
        int y = flotte[currentboat].getY();
    }
}