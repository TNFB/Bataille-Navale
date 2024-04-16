package com.CoMaTo.bataillenavale;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import java.security.Provider;

public class CheckWin extends Service {
    private final Handler handler = new Handler(Looper.getMainLooper());

    private game Game;

    @Override
    public void onCreate() {
        super.onCreate();
        Game = new game();
    }
    private final Runnable CheckWin = new Runnable() {
        @Override
        public void run() {
            if (game.ia_score == 24) {
                Game.checkEndGame(2);
            } else if (game.my_score == 24) {
                Game.checkEndGame(1);
            }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("lancé");
        handler.post(CheckWin);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("arreté");
        handler.removeCallbacks(CheckWin);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
}
