package com.CoMaTo.bataillenavale;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.CoMaTo.bataillenavale.databinding.ActivityEndGameBinding;

public class EndGame extends AppCompatActivity {
    private ActivityEndGameBinding binding;

    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onResume() {
        super.onResume();
        dataManager = new DataManager(this);
        binding.playerName.setText(dataManager.getPseudo());
        int extras = getIntent().getIntExtra("win", 0);
        if (extras == 1) {
            binding.resultat.setText("You win !");
        } else {
            binding.resultat.setText("You lose !");
        }

        binding.menu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}