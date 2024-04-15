package com.CoMaTo.bataillenavale;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class game extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Trouvez votre vue personnalisée par son ID
        gameView = findViewById(R.id.GameView);
    }
}
