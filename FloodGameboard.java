package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 30.05.16.
 */
public class FloodGameboard {

    public int size, numColor, width, height;
    Field[][] fields,fieldsOld;
    Gamemaster M;


    public FloodGameboard(int size, int numColor) {

        this.size = size;
        this.numColor = numColor;
        width = 50;
        height = 50;
    }


    public Field getField(int i, int j) {
        return fields[i][j];
    }
    public Field getFieldOld(int i, int j) {
        return fieldsOld[i][j];
    }


    public void undo(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = getFieldOld(i,j);
            }
        }


    }

    public void zug(int i, int j, int owner) {
        Field f = fields[i][j];
        checkPlanes(f.color, owner);
    }


    public void drawNewGame(int size, int numColor) {

        this.size = size;
        this.numColor = numColor;

        fields = new Field[size][size];
        fieldsOld = new Field[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field(numColor);
                fieldsOld[i][j] = fields[i][j];
            }
        }

        //Verhindere, dass beide startpositionen dieselbe farbe haben
        while (fields[0][0].color == fields[size - 1][size - 1].color) {
            fields[0][0] = new Field(numColor);
        }

    }


    protected void checkPlanes(Color color, int owner) {


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fieldsOld[i][j] = getField(i,j);
                if (fields[i][j].owner == owner) {
                    fields[i][j].color = color;

                }
            }
        }

        boolean found;
        do {
            found = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (fields[i][j].owner == owner) {

                        {
                            if (i > 0) {
                                Field f = fields[i - 1][j];

                                if (f.owner != owner && f.color == color) {
                                    f.owner = owner;
                                    found = true;
                                }
                            }
                        }

                        {
                            if (j > 0) {
                                Field f = fields[i][j - 1];

                                if (f.owner != owner && f.color == color) {
                                    f.owner = owner;
                                    found = true;
                                }
                            }
                        }

                        {
                            if (i < size - 1) {
                                Field f = fields[i + 1][j];

                                if (f.owner != owner && f.color == color) {
                                    f.owner = owner;
                                    found = true;
                                }
                            }
                        }
                        {
                            if (j < size - 1) {
                                Field f = fields[i][j + 1];

                                if (j < size - 1 && f.owner != owner && f.color == color) {
                                    f.owner = owner;
                                    found = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (found);
    }


    public int getScore(int player) {

        int score = score(player);
        return score;
    }

    private int score(int player) {
        int score = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getField(i, j).owner == player) {
                    score++;
                }
            }
        }
        return score;
    }

}


