package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CoMaTo.bataillenavale.databinding.FragmentFlotteMenuBinding;

import java.util.Objects;

public class flotte_menu extends Fragment {
    private FragmentFlotteMenuBinding binding;
    private DataManager dataManager;


    public static flotte_menu newInstance() {
        flotte_menu fragment = new flotte_menu();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = new DataManager(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFlotteMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        dataManager.initFlotte();
        binding.flotte1.setOnClickListener(v -> ConfigFlotte(1));
        binding.flotte2.setOnClickListener(v -> ConfigFlotte(2));
        binding.flotte3.setOnClickListener(v -> ConfigFlotte(3));
        binding.flotte4.setOnClickListener(v -> ConfigFlotte(4));
    }

    public void ConfigFlotte(int num_flotte){
        Bateau[] flotte = dataManager.getFlotte("flotte"+num_flotte);
        //PlacementView placementView = new PlacementView(getContext(), null);
    }
}