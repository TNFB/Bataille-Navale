package com.CoMaTo.bataillenavale;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.CoMaTo.bataillenavale.databinding.FragmentPlayerMenuBinding;

public class player_menu extends Fragment {
    private FragmentPlayerMenuBinding binding;
    private EditText editText;
    private ImageButton saveButton;

    DataManager dataManager;

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
        View view = binding.getRoot();

        editText = view.findViewById(R.id.editText);
        saveButton = view.findViewById(R.id.saveButton);
        TextView levelPrint = view.findViewById(R.id.levelPrint);

        DataManager dataManager = new DataManager(requireContext());
        String pseudo = dataManager.getPseudo();
        editText.setText(pseudo);

        int niveau = dataManager.getNiveau();
        levelPrint.setText(String.valueOf(niveau));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPseudo = editText.getText().toString().trim();

                if (!newPseudo.isEmpty()) {
                    // Sauvegarder le nouveau pseudo
                    dataManager.savePseudo(newPseudo);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
