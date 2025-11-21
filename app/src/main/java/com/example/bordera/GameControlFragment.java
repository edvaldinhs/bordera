package com.example.bordera;

// We remove the import for AsyncTask as it is deprecated
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.bordera.network.NetworkSender;

/**
 * Fragment containing the trigger buttons to manually send the launch signal.
 */
public class GameControlFragment extends Fragment {
    private static final String ARG_GAME_ID = "game_id";
    private String gameId;
    private TextView gameTitle;
    private TextView lastStatus;

    public static GameControlFragment newInstance(String gameId) {
        GameControlFragment fragment = new GameControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GAME_ID, gameId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gameId = getArguments().getString(ARG_GAME_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_control, container, false);

        gameTitle = view.findViewById(R.id.text_game_title);
        lastStatus = view.findViewById(R.id.text_last_status);
        Button btnTrigger = view.findViewById(R.id.btn_trigger_ping);
        Button btnEnd = view.findViewById(R.id.btn_end_ping);

        gameTitle.setText("Control Panel: " + gameId);

        btnTrigger.setOnClickListener(v -> sendTrigger(gameId, "START"));
        btnEnd.setOnClickListener(v -> sendTrigger(gameId, "STOP"));

        return view;
    }

    private void sendTrigger(String id, String action) {
        lastStatus.setText(String.format("Sending %s command for %s...", action, id));

        new Thread(() -> {
            NetworkSender sender = new NetworkSender();
            final boolean success = sender.sendGameTrigger(id, action);

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    if (success) {
                        lastStatus.setText(String.format("Last Command: SUCCESS! %s triggered for %s.", action, id));
                    } else {
                        lastStatus.setText(String.format("Last Command: FAILED! Check API or network. (%s)", action));
                    }
                });
            }
        }).start();
    }
}