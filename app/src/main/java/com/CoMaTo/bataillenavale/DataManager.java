package com.CoMaTo.bataillenavale;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class DataManager {

    private static final String PREF_NAME = "MyPreferences";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public DataManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Enregistrer les données de position des bateaux
    public void saveFlotte(Bateau[] flotte, String key_flotte) {
        String flotteJson = gson.toJson(flotte);
        sharedPreferences.edit().putString(key_flotte, flotteJson).apply();
    }

    // Récupérer les données de position des bateaux
    public Bateau[] getFlotte(String key_flotte) {
        String flotteJson = sharedPreferences.getString(key_flotte, null);
        if (flotteJson != null) {
            return gson.fromJson(flotteJson, Bateau[].class);
        } else {
            return new Bateau[0];
        }
    }

    public void deleteFlotte(String key_flotte) {
        sharedPreferences.edit().remove(key_flotte).apply();
    }

    public void initFlotte(){
        for (int i = 1; i <= 4; i++) {
            if (getFlotte("flotte" + i).length == 0) {
                deleteFlotte("flotte" + i);
            }
        }
        Bateau[] flotte = new Bateau[6];
        flotte[0] = new Bateau(1, 0, 2, 1,R.drawable.bateau_2, Bateau.getMatriceBoat(1));
        flotte[1] = new Bateau(5, 1, 2,4,R.drawable.bateau_3_3, Bateau.getMatriceBoat(2));
        flotte[2] = new Bateau(2, 3, 1, 3,R.drawable.bateau_3, Bateau.getMatriceBoat(3));
        flotte[3] = new Bateau(0, 6, 2, 3,R.drawable.bateau_3_1, Bateau.getMatriceBoat(4));
        flotte[4] = new Bateau(5, 5, 1, 4,R.drawable.bateau_4, Bateau.getMatriceBoat(5));
        flotte[5] = new Bateau(8, 5, 1, 5,R.drawable.bateau_5, Bateau.getMatriceBoat(6));
        saveFlotte(flotte, "flotte1");
        flotte = new Bateau[6];
        flotte[0] = new Bateau(0, 9, 2, 1,R.drawable.bateau_2,Bateau.getMatriceBoat(1));
        flotte[1] = new Bateau(7, 0, 2,4,R.drawable.bateau_3_3,Bateau.getMatriceBoat(2));
        flotte[2] = new Bateau(2, 3, 1, 3,R.drawable.bateau_3,Bateau.getMatriceBoat(3));
        flotte[3] = new Bateau(1, 6, 2, 3,R.drawable.bateau_3_1,Bateau.getMatriceBoat(4));
        flotte[4] = new Bateau(5, 5, 1, 4,R.drawable.bateau_4,Bateau.getMatriceBoat(5));
        flotte[5] = new Bateau(8, 5, 1, 5,R.drawable.bateau_5,Bateau.getMatriceBoat(6));
        saveFlotte(flotte, "flotte2");

        flotte = new Bateau[6];
        flotte[0] = new Bateau(1, 0, 2, 1,R.drawable.bateau_2, Bateau.getMatriceBoat(1));
        flotte[1] = new Bateau(5, 1, 2,4,R.drawable.bateau_3_3, Bateau.getMatriceBoat(2));
        flotte[2] = new Bateau(2, 3, 1, 3,R.drawable.bateau_3, Bateau.getMatriceBoat(3));
        flotte[3] = new Bateau(0, 6, 2, 3,R.drawable.bateau_3_1, Bateau.getMatriceBoat(4));
        flotte[4] = new Bateau(5, 5, 1, 4,R.drawable.bateau_4, Bateau.getMatriceBoat(5));
        flotte[5] = new Bateau(8, 5, 1, 5,R.drawable.bateau_5, Bateau.getMatriceBoat(6));
        saveFlotte(flotte, "flotte3");
        flotte = new Bateau[6];
        flotte[0] = new Bateau(0, 9, 2, 1,R.drawable.bateau_2,Bateau.getMatriceBoat(1));
        flotte[1] = new Bateau(7, 0, 2,4,R.drawable.bateau_3_3,Bateau.getMatriceBoat(2));
        flotte[2] = new Bateau(2, 3, 1, 3,R.drawable.bateau_3,Bateau.getMatriceBoat(3));
        flotte[3] = new Bateau(1, 6, 2, 3,R.drawable.bateau_3_1,Bateau.getMatriceBoat(4));
        flotte[4] = new Bateau(5, 5, 1, 4,R.drawable.bateau_4,Bateau.getMatriceBoat(5));
        flotte[5] = new Bateau(8, 5, 1, 5,R.drawable.bateau_5,Bateau.getMatriceBoat(6));
        saveFlotte(flotte, "flotte4");
    }

    // Enregistrer le pseudo
    public void savePseudo(String pseudo) {
        sharedPreferences.edit().putString("pseudo", pseudo).apply();
    }

    // Récupérer le pseudo
    public String getPseudo() {
        String pseudo = sharedPreferences.getString("pseudo", "");
        if (pseudo.isEmpty()) {
            pseudo = "Joueur";
            savePseudo(pseudo);
        }
        return pseudo;
    }

    // Récupérer le niveuau du joueur
    public int getNiveau() {
        return sharedPreferences.getInt("niveau", 1);
    }

    // Monter de niveau
    public void upNiveau() {
        int niveau = getNiveau();
        sharedPreferences.edit().putInt("niveau", niveau + 1).apply();
    }
}

