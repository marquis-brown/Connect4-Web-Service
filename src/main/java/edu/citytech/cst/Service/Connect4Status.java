package edu.citytech.cst.Service;

import java.util.Arrays;
/*
    Tells the status of a Connect 4 board
*/
public class Connect4Status {

    private boolean isWinner;   // Has a winner been determined
    private int[] position;     // What combination has won
    private String whoWon;      // Which player has won

    public Connect4Status(boolean isWinner, int[] position, String whoWon){
        this.isWinner = isWinner;
        this.position = position;
        this.whoWon = whoWon;
    }

    public boolean getIsWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getWhoWon() {
        return whoWon;
    }

    public void setWhoWon(String whoWon) {
        this.whoWon = whoWon;
    }

    @Override
    public String toString() {
        return "TicTacToeStatus{" +
                "isWinner=" + isWinner +
                ", position=" + Arrays.toString(position) +
                ", whoWon='" + whoWon + '\'' +
                '}';
    }
}

