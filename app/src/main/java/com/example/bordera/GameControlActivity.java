package com.example.bordera;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class GameControlActivity extends AppCompatActivity {
    public static final String EXTRA_GAME_ID = "com.example.bordera.game_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_control); // Assume a simple FrameLayout to hold the fragment

        String gameId = getIntent().getStringExtra(EXTRA_GAME_ID);
        if (gameId == null) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, GameControlFragment.newInstance(gameId))
                    .commit();
        }
        setTitle("Control: " + gameId);
    }
}