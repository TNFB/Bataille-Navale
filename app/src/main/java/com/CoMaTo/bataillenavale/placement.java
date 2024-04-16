package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class placement extends AppCompatActivity {
    private PlacementView placementView;
    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        placementView = findViewById(R.id.GameView);
        dataManager = new DataManager(this);
        dataManager.initFlotte();
        placementView.setBateaux(dataManager.getFlotte("flotte1"));
    }
}
