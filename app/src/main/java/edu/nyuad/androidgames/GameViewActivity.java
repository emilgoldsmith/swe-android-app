package edu.nyuad.androidgames;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.TicTacToe;

public class GameViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        final GridView boardGrid = (GridView)findViewById(R.id.boardGrid);
        Game model = new TicTacToe();
        boardGrid.setNumColumns(model.getColumns());
        final BoardAdapter boardAdapter = new BoardAdapter(this, model);
        boardGrid.setAdapter(boardAdapter);
    }
}
