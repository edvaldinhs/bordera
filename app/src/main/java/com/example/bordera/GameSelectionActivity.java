package com.example.bordera;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bordera.adapters.GameAdapter;
import com.example.bordera.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_games);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Game> games = createGameList();

        GameAdapter adapter = new GameAdapter(this, games);
        recyclerView.setAdapter(adapter);
    }

    private List<Game> createGameList() {
        List<Game> games = new ArrayList<>();

        games.add(new Game(
                "ACE of Spades: Miles",
                "ACE_OF_SPADES",
                R.color.teal_700 // colors.xml
        ));

        games.add(new Game(
                "8 of Hearts: Spy",
                "EIGHT_OF_HEARTS",
                R.color.cyan_700
        ));

        games.add(new Game(
                "4 de Paus: Alavanca",
                "FOUR_OF_CLUBS",
                R.color.orange_400
        ));

        games.add(new Game(
                "4 de Espadas: Páscoa",
                "FOUR_OF_SPADES",
                R.color.purple_500
        ));
        games.add(new Game(
                "7 de Ouros: Blind Dealer",
                "SEVEN_OF_DIAMONDS",
                R.color.black
        ));

        return games;
    }
}