package edu.nyuad.androidgames;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.GameStateException;
import edu.nyuad.boardgames.TicTacToe;

public class GameViewActivity extends AppCompatActivity {
    private Game model;
    private BoardAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        // Initialize our model
        model = new TicTacToe();

        final GridView boardGrid = (GridView)findViewById(R.id.boardGrid);
        boardGrid.setNumColumns(model.getColumns());
        boardAdapter = new BoardAdapter(this, model);
        boardGrid.setAdapter(boardAdapter);

        // Initialize
        this.modelChanged();
    }

    public void modelChanged() {
        // Update board
        boardAdapter.notifyDataSetChanged();

        // Set current player
        ImageView currentPlayerSquare = (ImageView)findViewById(R.id.current_player);
        Chip currentPlayer = model.getCurrentPlayer();
        int colorReference;
        if (currentPlayer == Chip.BLUE) {
            colorReference = R.color.blue;
        } else if (currentPlayer == Chip.RED){
            colorReference = R.color.red;
        } else {
            colorReference = R.color.unclaimed;
        }
        int squareColor = getResources().getColor(colorReference);

        currentPlayerSquare.setBackgroundColor(squareColor);

        // Check if there is any win
        if (model.isGameOver()) {
            handleWin();
        }
    }

    private void handleWin() {
        String notificationString;
        try {
            Chip winner = model.getWinningPlayer();
            if (winner == Chip.BLUE) {
                notificationString = "Blue won the game";
            } else {
                notificationString = "Red won the game";
            }
        } catch (GameStateException e) {
            notificationString = "The game ended in a draw";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(notificationString + ", would you like to play again?").setTitle("Game Over");
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                model = new TicTacToe();
                boardAdapter.updateModel(model);
                modelChanged();
            }
        });
        builder.setNegativeButton("Return to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.show();
    }
}
