package com.CoMaTo.bataillenavale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

public class PlacementView extends View {
    private static final String[] LETTER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static final String[] NUMBER = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final int[] boatDrawable = new int[]{R.drawable.bateau_2, R.drawable.bateau_3_3,R.drawable.bateau_3, R.drawable.bateau_3_1, R.drawable.bateau_4, R.drawable.bateau_5};
    private final int startX;
    private final int startY;
    private final int cellSize;
    private static final int GRID_SIZE = 10;
    private final int grid_width;
    private Bateau[] flotte = new Bateau[6];
    private Bitmap backBitmap;
    private final Bitmap[] bateaux = new Bitmap[6];
    private int offsetX, offsetY;
    private boolean isDragging = false;

    private int currentBoat = -1;
    public PlacementView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //initialisation des variables selon la taille de l'écran du téléphone
        int phoneWidth = context.getResources().getDisplayMetrics().widthPixels;
        grid_width = (int) (phoneWidth * 0.88);
        cellSize = grid_width / GRID_SIZE;
        startX = (int) (grid_width * 0.06);
        startY = (int) (grid_width * 0.05);

        setBackBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        drawGrid(canvas);
        drawText(canvas);
        drawBackImage(canvas);
        drawBateaux(canvas);
    }

    public void setBateaux(Bateau[] boats){
        flotte = boats;
        for(int i = 0; i < 6; i++) {
            bateaux[i] = BitmapFactory.decodeResource(getResources(), boatDrawable[i]);
            bateaux[i] = Bitmap.createScaledBitmap(bateaux[i], flotte[i].getTaille_x() * cellSize - 10, flotte[i].getTaille_y() * cellSize - 10, false);
        }
        invalidate();
    }

    private void setBackBitmap(){
        backBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_grid);
        backBitmap = Bitmap.createScaledBitmap(backBitmap, grid_width, grid_width, false);
    }

    public Bateau[] getFlotte(){
        return flotte;
    }

    @SuppressLint("ClickableViewAccessibility")
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
                    offsetY = (int) event.getY() / cellSize - flotte[currentBoat].getY();
                    offsetX = (int) event.getX() / cellSize - flotte[currentBoat].getX();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging && currentBoat != -1) {
                    int currentX = flotte[currentBoat].getX();
                    int currentY = flotte[currentBoat].getY();
                    flotte[currentBoat].setX((int) (event.getX() - offsetX)/cellSize);
                    flotte[currentBoat].setY((int) (event.getY() - offsetY)/cellSize);
                    isInside(currentBoat);
                    alreadyPlaced(currentX, currentY, currentBoat);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }

    private void drawText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        for (int i = 0; i < GRID_SIZE; i++) {
            canvas.drawText(LETTER[i], startX + (i * cellSize) + 32, startY - 20, paint);
            canvas.drawText(NUMBER[i], startX - 55, startY + (i * cellSize) + 62, paint);
        }
    }

    private void drawBackImage(Canvas canvas){
        canvas.drawBitmap(backBitmap, startX, startY, new Paint());
    }
    private void drawGrid(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = startX + (i * cellSize);
            canvas.drawLine(x, startY, x, startY + grid_width, paint);
        }
        for (int j = 0; j <= GRID_SIZE; j++) {
            int y = startY + (j * cellSize);
            canvas.drawLine(startX, y, startX + grid_width, y, paint);
        }
    }
    private void drawBateaux(Canvas canvas){
        for(int i = 0; i < 6; i++){
            canvas.drawBitmap(bateaux[i], startX + flotte[i].getX() * cellSize, startY + flotte[i].getY() * cellSize, new Paint());
        }
    }

    private void isInside(int currentboat){
        int x = flotte[currentboat].getX();
        int y = flotte[currentboat].getY();
        int taille_x = flotte[currentboat].getTaille_x();
        int taille_y = flotte[currentboat].getTaille_y();
        if(x < 0){
            flotte[currentboat].setX(0);
        }
        if(x + taille_x > 10){
            flotte[currentboat].setX(10 - taille_x);

        }
        if(y < 1){
            flotte[currentboat].setY(0);
        }
        if(y + taille_y > 10){
            flotte[currentboat].setY(10 - taille_y);
        }
    }

    private int whatboat(int x, int y){
        int case_x = (x - startX) / cellSize;
        int case_y = (y - startY) / cellSize;
        System.out.println("case_x: " + case_x + " case_y: " + case_y);
        for(int i = 0; i < 6; i++){
            if(case_x >= flotte[i].getX() && case_x <= flotte[i].getX() + flotte[i].getTaille_x() -1 && case_y >= flotte[i].getY() && case_y <= flotte[i].getY() + flotte[i].getTaille_y() - 1){
                return i;
            }
        }
        return -1;
    }

    private void alreadyPlaced(int currentX, int currentY, int currentboat) {
        int x = flotte[currentboat].getX();
        int y = flotte[currentboat].getY();
        for (int i = 0; i < 6; i++) {
            if (i != currentboat) {
                if (x >= flotte[i].getX() && x <= flotte[i].getX() + flotte[i].getTaille_x() - 1 && y >= flotte[i].getY() && y <= flotte[i].getY() + flotte[i].getTaille_y() - 1) {
                    flotte[currentboat].setX(currentX);
                    flotte[currentboat].setY(currentY);
                }
            }
        }
    }
}