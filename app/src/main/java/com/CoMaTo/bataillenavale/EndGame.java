package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.CoMaTo.bataillenavale.databinding.ActivityEndGameBinding;

public class EndGame extends AppCompatActivity {
    private ActivityEndGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}