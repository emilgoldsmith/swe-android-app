package edu.nyuad.androidgames;

public class Square {
    private int squareOwner;

    public Square() {
        squareOwner = 0;
    }

    public Square(int startingColor) {
        squareOwner = startingColor;
    }

    public int getOwner() {
        return squareOwner;
    }

    public void setOwner(int newOwner) {
        squareOwner = newOwner;
    }
}
