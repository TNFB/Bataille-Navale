package com.CoMaTo.bataillenavale;

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
        //ft.add(R.id.fragment_container, player_menu.newInstance());
        //ft.add(R.id.fragment_container, flotte_menu.newInstance());
        ft.commit();
    }

}