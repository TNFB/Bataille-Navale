package com.CoMaTo.bataillenavale;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GameView extends View {
    private static String[] LETTER = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[] NUMBER = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static int[] boatDrawable = new int[]{R.drawable.bateau_2, R.drawable.bateau_3_3,R.drawable.bateau_3, R.drawable.bateau_3_1, R.drawable.bateau_4, R.drawable.bateau_5};
    private static int[] hitDrawable = new int[]{R.drawable.cross, R.drawable.dot};
    private int startX, startY, cellSize;
    private static final int GRID_SIZE = 10;
    private int grid_width;
    private Bateau[] flotte = new Bateau[6];
    private Bitmap backBitmap;
    private Bitmap[] bateaux = new Bitmap[6];
    ArrayList<Hit> my_hits = new ArrayList<>();
    ArrayList<Bitmap> my_impacts = new ArrayList<>();
    ArrayList<Hit> ia_hits = new ArrayList<>();
    ArrayList<Bitmap> ia_impacts = new ArrayList<>();

    public static boolean endgame = false;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private AlertDialog.Builder tour = new AlertDialog.Builder(this.getContext());
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
            drawHits(canvas);

        } else {
            drawGrid(canvas);
            drawText(canvas);
            drawBackImage(canvas);
            drawBateaux(canvas);
            drawHits(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) ((event.getX() - startX ) / cellSize);
            int y = (int) ((event.getY() - startY) / cellSize);
            if(game.getTurn()){
                Hit hit = game.play(game.my_grid, x, y);
                if(hit != null) {
                    addHits(hit);
                    invalidate();
                    if(hit.getTypeHit()==1){
                        game.my_score++;
                        if(endgame){
                            Intent intent = new Intent(getContext(), CheckWin.class);
                            getContext().stopService(intent);
                            intent = new Intent(getContext(), EndGame.class);
                            intent.putExtra("win", 1);
                            getContext().startActivity(intent);
                        }
                    }
                    scheduler.schedule(new Runnable() {
                        @Override
                        public void run() {
                            game.setTurn(!game.getTurn());
                            invalidate();
                            ia_turn();
                        }
                    }, 1, TimeUnit.SECONDS);
                }
            }
        }
        return true;
    }
    public void setBateaux(Bateau[] boats){
        System.out.println("bateaux ajoutés");
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

    public void addHits(Hit hit){
        if(game.getTurn()){
            my_hits.add(hit);
            Bitmap hitBitmap = BitmapFactory.decodeResource(getResources(), hitDrawable[hit.getTypeHit()]);
            hitBitmap = Bitmap.createScaledBitmap(hitBitmap, cellSize, cellSize, false);
            my_impacts.add(hitBitmap);
        } else {
            ia_hits.add(hit);
            Bitmap hitBitmap = BitmapFactory.decodeResource(getResources(), hitDrawable[hit.getTypeHit()]);
            hitBitmap = Bitmap.createScaledBitmap(hitBitmap, cellSize, cellSize, false);
            ia_impacts.add(hitBitmap);
        }
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

    private void drawHits(Canvas canvas){
        if(game.getTurn()){
            for(int i = 0; i < my_impacts.size(); i++){
                canvas.drawBitmap(my_impacts.get(i), startX + my_hits.get(i).getX() * cellSize, startY + my_hits.get(i).getY() * cellSize, new Paint());
            }
        }
        else {
            for(int i = 0; i < ia_impacts.size(); i++){
                canvas.drawBitmap(ia_impacts.get(i), startX + ia_hits.get(i).getX() * cellSize, startY + ia_hits.get(i).getY() * cellSize, new Paint());
            }
        }
    }

    private void ia_turn(){
        int[] ia_play = IA.randomClick();
        Hit hit = game.play(game.ia_grid, ia_play[0], ia_play[1]);
        if(hit != null) {
            addHits(hit);
            if(hit.getTypeHit()==1){
                game.ia_score++;
            }
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, 500, TimeUnit.MILLISECONDS);
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    game.setTurn(!game.getTurn());
                    invalidate();
                }
            }, 1500, TimeUnit.MILLISECONDS);
        } else {
            ia_turn();
        }
        if(endgame){
            Intent intent = new Intent(getContext(), CheckWin.class);
            getContext().stopService(intent);
            intent = new Intent(getContext(), EndGame.class);
            intent.putExtra("win", 2);
            getContext().startActivity(intent);
        }
    }
}