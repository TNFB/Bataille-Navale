package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CoMaTo.bataillenavale.databinding.FragmentConfMenuBinding;
import com.CoMaTo.bataillenavale.databinding.FragmentFlotteMenuBinding;
import com.CoMaTo.bataillenavale.databinding.FragmentPlayerMenuBinding;

public class player_menu extends Fragment {
    private FragmentPlayerMenuBinding binding;
    public static player_menu newInstance() {
        player_menu fragment = new player_menu();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayerMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}