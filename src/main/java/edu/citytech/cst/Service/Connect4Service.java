package edu.citytech.cst.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Connect4Service {

    static List<C4WinningCombo> list = new ArrayList<>();

    static {
        loadCombo();
    }
    
    static List<String> readCSV() {
        try (InputStream inputStream = Connect4Service.class.getResourceAsStream("/C4WinningCombo.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> contents = reader.lines().collect(Collectors.toList());

            return contents;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private static void loadCombo(){
        List<String> lines = readCSV();
        for (String row: lines) {
            int w1,w2,w3,w4;
            String[] columns = row.split(",");
            w1 = Integer.parseInt(columns[0]);
            w2 = Integer.parseInt(columns[1]);
            w3 = Integer.parseInt(columns[2]);
            w4 = Integer.parseInt(columns[3]);
            C4WinningCombo status = new C4WinningCombo(w1,w2,w3,w4);
            list.add(status);
        }
    }

    /*
        Accepts the moves on the Connect 4 board
        Returns a Connect4Status that shows if a play has winner
    */
    public Connect4Status getStatus(String moves){
        char[] allMoves = moves.toCharArray();

        Connect4Status status = new Connect4Status(false, new int[0], "?");

        // Loop through winning combinations until we find a match
        for (C4WinningCombo wc : list) {
            if(allMoves[wc.w1] == allMoves[wc.w2] && allMoves[wc.w2] == allMoves[wc.w3]
                    && allMoves[wc.w3] == allMoves[wc.w4] && allMoves[wc.w1] != '?'){
                status = new Connect4Status(true, new int[] {wc.w1,wc.w2,wc.w3,wc.w4}, allMoves[wc.w1]+"");
                break;
            }
        }
        return status;
    }
}
