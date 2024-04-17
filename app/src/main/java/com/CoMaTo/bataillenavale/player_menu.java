package com.CoMaTo.bataillenavale;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.CoMaTo.bataillenavale.databinding.FragmentPlayerMenuBinding;

public class player_menu extends Fragment {
    private EditText editText;

    public static player_menu newInstance() {
        return new player_menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlayerMenuBinding binding = FragmentPlayerMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        editText = view.findViewById(R.id.editText);
        ImageButton saveButton = view.findViewById(R.id.saveButton);
        TextView levelPrint = view.findViewById(R.id.levelPrint);

        DataManager dataManager = new DataManager(requireContext());
        String pseudo = dataManager.getPseudo();
        editText.setText(pseudo);

        int niveau = dataManager.getNiveau();
        levelPrint.setText(String.valueOf(niveau));

        saveButton.setOnClickListener(v -> {
            String newPseudo = editText.getText().toString().trim();

            if (!newPseudo.isEmpty()) {
                dataManager.savePseudo(newPseudo);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
