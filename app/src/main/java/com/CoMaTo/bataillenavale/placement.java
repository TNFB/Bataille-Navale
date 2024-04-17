package com.CoMaTo.bataillenavale;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.CoMaTo.bataillenavale.databinding.ActivityMainBinding;
import com.CoMaTo.bataillenavale.databinding.FragmentFlotteMenuBinding;

public class placement extends AppCompatActivity {
    private PlacementView placementView;
    private FragmentFlotteMenuBinding binding;
    private DataManager dataManager;

    private String currentFlotte = "flotte1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_flotte_menu);
        binding = FragmentFlotteMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        placementView = findViewById(R.id.PlacementView);
        dataManager = new DataManager(this);
        placementView.setBateaux(dataManager.getFlotte("flotte1"));
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.save.setOnClickListener(v -> {
            dataManager.saveCurrentFloat(currentFlotte);
            dataManager.deleteFlotte(currentFlotte);
            dataManager.saveFlotte(placementView.getFlotte(),currentFlotte);
        });

        binding.flotte1.setOnClickListener(v -> {
            currentFlotte = "flotte1";
            binding.numeroFlotte.setText("Flotte 1");
            placementView.setBateaux(dataManager.getFlotte("flotte1"));
        });

        binding.flotte2.setOnClickListener(v -> {
            currentFlotte = "flotte2";
            binding.numeroFlotte.setText("Flotte 2");
            placementView.setBateaux(dataManager.getFlotte("flotte2"));
        });

        binding.flotte3.setOnClickListener(v -> {
            currentFlotte = "flotte3";
            binding.numeroFlotte.setText("Flotte 3");
            placementView.setBateaux(dataManager.getFlotte("flotte3"));
        });

        binding.flotte4.setOnClickListener(v -> {
            currentFlotte = "flotte4";
            binding.numeroFlotte.setText("Flotte 4");
            placementView.setBateaux(dataManager.getFlotte("flotte4"));
        });

        binding.back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }


}