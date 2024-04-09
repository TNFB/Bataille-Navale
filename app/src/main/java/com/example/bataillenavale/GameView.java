package com.example.bataillenavale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private Bitmap backBitmap;
    /*
    private Bitmap boatBitmap;
    private int boatX, boatY;
    private int offsetX, offsetY;
    private boolean isDragging = false;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        boatBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bouton_jouer_v9);
        boatX = 100;  // Position initiale X
        boatY = 100;  // Position initiale Y
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dessiner le bateau
        canvas.drawBitmap(boatBitmap, boatX, boatY, new Paint());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (isInsideBoat(event.getX(), event.getY())) {
                    isDragging = true;
                    offsetX = (int) event.getX() - boatX;
                    offsetY = (int) event.getY() - boatY;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    boatX = (int) event.getX() - offsetX;
                    boatY = (int) event.getY() - offsetY;
                    invalidate();  // Redessiner le Canvas
                }
                break;
            case MotionEvent.ACTION_UP:
                isDragging = false;
                break;
        }
        return true;
    }

    private boolean isInsideBoat(float x, float y) {
        int width = boatBitmap.getWidth();
        int height = boatBitmap.getHeight();

        // Créer un rectangle autour du bateau
        RectF rectF = new RectF(boatX, boatY, boatX + width, boatY + height);

        return rectF.contains(x, y);
    }*/
    private static final int GRID_SIZE = 10;

    private static int SIZE = 950;
    private int[][] grid; // Matrice pour la grille
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        backBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_grid);
        initGrid();
    }

    private void initGrid(){
        grid = new int[GRID_SIZE][GRID_SIZE];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int cellSize = SIZE / GRID_SIZE;
        int start_x = 65;
        int start_y = 50;
        Paint paint = new Paint();
        canvas.drawBitmap(backBitmap, start_x, start_y, new Paint());
        //paint.setColor(Color.BLUE);
        //canvas.drawRect(start_x, start_y, start_x + SIZE, start_y + SIZE, paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        for (int i = 0; i <= GRID_SIZE; i++) {
            int x = start_x + (i * cellSize);
            canvas.drawLine(x, start_y, x, start_y + SIZE, paint);
        }
        for (int j = 0; j <= GRID_SIZE; j++) {
            int y = start_y + (j * cellSize);
            canvas.drawLine(start_x, y, start_x + SIZE, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        invalidate();
        return true;
    }
}