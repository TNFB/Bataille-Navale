package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.CoMaTo.bataillenavale.databinding.FragmentConfMenuBinding;
import com.CoMaTo.bataillenavale.databinding.FragmentMainMenuBinding;

public class conf_menu extends Fragment {
    private FragmentConfMenuBinding binding;
    public static conf_menu newInstance() {
        conf_menu fragment = new conf_menu();
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
        binding = FragmentConfMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}