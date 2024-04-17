package com.CoMaTo.bataillenavale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        dataManager = new DataManager(this);
        binding.playerName.setText(dataManager.getPseudo());
        int extras = getIntent().getIntExtra("win",0);
        if (extras == 1) {
            binding.resultat.setText("You win ! Level up !");
            dataManager.upNiveau();
        } else if(extras == 2){
            binding.resultat.setText("You lose !");
        }

        binding.menu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}