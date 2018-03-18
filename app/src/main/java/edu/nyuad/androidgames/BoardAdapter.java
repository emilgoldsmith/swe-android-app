package edu.nyuad.androidgames;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.nyuad.boardgames.Chip;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;
import edu.nyuad.boardgames.GameStateException;

public class BoardAdapter extends BaseAdapter {
    private final Context context;
    private final Game model;

    public BoardAdapter(Context context, Game model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return this.model.getColumns() * this.model.getRows();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int row = position / this.model.getRows();
        final int column = position % this.model.getRows();

        Chip currentOwner = this.model.getChip(row, column);
        int colorReference;
        if (currentOwner.isEmpty()) {
            colorReference = R.color.unclaimed;
        } else if (currentOwner == Chip.BLUE) {
            colorReference = R.color.blue;
        } else {
            colorReference = R.color.red;
        }

        int squareColor = context.getResources().getColor(colorReference);
        GradientDrawable squareBackground = new GradientDrawable();
        squareBackground.setColor(squareColor);
        // These are just empirically decided
        squareBackground.setCornerRadius(20);
        squareBackground.setStroke(10, 0);
        Button myButton = new Button(context);
        myButton.setBackground(squareBackground);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    model.placeChip(row, column);
                    notifyDataSetChanged();
                } catch (GameStateException e) {
                    Toast.makeText(context, "The game is already over", Toast.LENGTH_SHORT).show();
                } catch (GameIndexOutOfBoundsException e) {
                    Toast.makeText(context, "That is an invalid position to place a chip", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myButton;
    }
}
