package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CoMaTo.bataillenavale.databinding.FragmentConfMenuBinding;
import com.CoMaTo.bataillenavale.databinding.FragmentFlotteMenuBinding;

public class flotte_menu extends Fragment {

    private FragmentFlotteMenuBinding binding;

    public static flotte_menu newInstance() {
        flotte_menu fragment = new flotte_menu();
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
        binding = FragmentFlotteMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}