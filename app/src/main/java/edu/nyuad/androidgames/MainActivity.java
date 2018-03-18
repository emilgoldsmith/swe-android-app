package edu.nyuad.androidgames;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    Button ticTacToeButton;
    public static final String GAME_TYPE_MESSAGE = "GAME_TYPE_MESSAGE";
    private static final Map<String, String> games;
    static {
        games = new TreeMap<>();
        games.put("Tic Tac Toe", "edu.nyuad.boardgames.TicTacToe");
        games.put("Connect Four", "edu.nyuad.boardgames.ConnectFour");
        games.put("Complica", "edu.nyuad.boardgames.Complica");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.menuButtonLayout);
        for (final Map.Entry<String, String> entry: games.entrySet()) {
            // We create a button for each entry
            Button button = new Button(this);
            button.setText(entry.getKey());
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), GameViewActivity.class);
                    intent.putExtra(GAME_TYPE_MESSAGE, entry.getValue());
                    startActivity(intent);
                }
            });
            buttonLayout.addView(button);
        }
    }
}
