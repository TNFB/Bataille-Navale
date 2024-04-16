package com.CoMaTo.bataillenavale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class GameView extends View {
    private static String[] LETTER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[] NUMBER = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static int[] boatDrawable = new int[]{R.drawable.bateau_2, R.drawable.bateau_3_3,R.drawable.bateau_3, R.drawable.bateau_3_1, R.drawable.bateau_4, R.drawable.bateau_5};
    private int startX, startY, cellSize;
    private static final int GRID_SIZE = 10;
    private int grid_width;
    private Bitmap backBitmap;
    private Bitmap[] bateaux = new Bitmap[6];

    private Bateau[] flotte = new Bateau[6];
    public GameView(Context context, AttributeSet attrs) {
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
        if(game.getTurn()) {
            drawGrid(canvas);
            drawText(canvas);
            drawBackImage(canvas);
            game.setTurn(false);
        } else {
            drawGrid(canvas);
            drawText(canvas);
            drawBackImage(canvas);
            drawBateaux(canvas);
            game.setTurn(true);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //au clic on change la vue avec bateau ou sans bateau en alternance
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //on redessine la grille, le fond et on supprime les bateaux
            invalidate();
        }

        return true;
    }

    public void setBateaux(Bateau[] boats){
        flotte = boats;
        for(int i = 0; i < 6; i++){
            bateaux[i] = BitmapFactory.decodeResource(getResources(), boatDrawable[i]);
            bateaux[i] = Bitmap.createScaledBitmap(bateaux[i], flotte[i].getTaille_x() * cellSize - 10, flotte[i].getTaille_y() * cellSize - 10, false);
        }
    }

    private void setBackBitmap(){
        backBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_grid);
        backBitmap = Bitmap.createScaledBitmap(backBitmap, grid_width, grid_width, false);
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
}