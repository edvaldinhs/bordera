package com.example.bordera.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bordera.GameControlActivity;
import com.example.bordera.R;
import com.example.bordera.models.Game;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private final List<Game> gameList;
    private final Context context;

    public GameAdapter(Context context, List<Game> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gameList.get(position);

        String buttonText = String.format("%d. %s (ID: %s)", position + 1, game.getName(), game.getId());
        holder.gameButton.setText(buttonText);

        int color = ContextCompat.getColor(context, game.getColorResId());
        holder.gameButton.setBackgroundTintList(ContextCompat.getColorStateList(context, game.getColorResId()));

        holder.gameButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameControlActivity.class);
            intent.putExtra(GameControlActivity.EXTRA_GAME_ID, game.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public Button gameButton;

        public GameViewHolder(View itemView) {
            super(itemView);
            gameButton = itemView.findViewById(R.id.btn_game_item);
        }
    }
}