package com.CoMaTo.bataillenavale;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

public class CheckWin extends Service {
    private final Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void onCreate() {
        super.onCreate();
    }
    private final Runnable CheckWin = new Runnable() {
        @Override
        public void run() {
            if (game.ia_score == 24) {
                GameView.endgame = true;
            } else if (game.my_score == 24) {
                GameView.endgame = true;
            }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(CheckWin);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(CheckWin);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
