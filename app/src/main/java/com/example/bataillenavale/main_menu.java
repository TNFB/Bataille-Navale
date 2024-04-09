package com.example.bataillenavale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bataillenavale.databinding.FragmentMainMenuBinding;

public class main_menu extends Fragment {
    private FragmentMainMenuBinding binding;
    public static main_menu newInstance() {
        main_menu fragment = new main_menu();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), game.class);
                startActivity(intent);
            }
        });
    }
}