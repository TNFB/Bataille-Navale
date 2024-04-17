package com.CoMaTo.bataillenavale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.CoMaTo.bataillenavale.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, main_menu.newInstance());
        ft.commit();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onResume() {
        super.onResume();
        binding.playerButton.setOnClickListener(v -> {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            binding.main.setBackground(getDrawable(R.drawable.background_profil));
            ft.replace(R.id.fragment_container, player_menu.newInstance());
            ft.commit();
        });
        binding.menuPlayButton.setOnClickListener(v -> {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            binding.main.setBackground(getDrawable(R.drawable.background));
            ft.replace(R.id.fragment_container, main_menu.newInstance());
            ft.commit();
        });
        binding.flotteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, placement.class);
            startActivity(intent);
        });
    }
}