package com.CoMaTo.bataillenavale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CoMaTo.bataillenavale.databinding.FragmentMainMenuBinding;
import com.CoMaTo.bataillenavale.databinding.ActivityMainBinding;

import java.util.Objects;

public class main_menu extends Fragment {
    private FragmentMainMenuBinding binding;

    private DataManager dataManager;
    private ActivityMainBinding binding2;
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
        binding2 = ActivityMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager = new DataManager(getContext());
                Intent intent = new Intent(getActivity(), game.class);
                startActivity(intent);
            }
        });
    }
}