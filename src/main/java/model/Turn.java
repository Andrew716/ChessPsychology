package model;

/**
 * Created by phoenix on 04.01.17.
 */
public class Turn {

    private String turn;
    private int numberOfTurn;

    public Turn(String turn, int numberOfTurn) {
        this.turn = turn;
        this.numberOfTurn = numberOfTurn;
    }

    public String getTurn() {
        return turn;
    }

    public int getNumberOfTurn() {
        return numberOfTurn;
    }
}
