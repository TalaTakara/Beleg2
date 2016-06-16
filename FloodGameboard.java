package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 30.05.16.
 */
public class FloodGameboard {

    public int size, numColor, width, height;
    Field[][] fields;
    Field[][] fieldsOld;
    Gamemaster M;

    /**
     * @param size     Größe des quardratischen Spielfeldes
     * @param numColor Anzahl der Farben ( max 6)
     */
    public FloodGameboard(int size, int numColor) {

        this.size = size;
        this.numColor = numColor;
        width = 50;
        height = 50;
    }

    /**
     * @param i x-Kordinate
     * @param j y-Koordinate
     * @return gibt ein Feld der Spielfeldes wieder
     */
    public Field getField(int i, int j) {
        return fields[i][j];
    }

    public Field getFieldOld(int i, int j) {
        return fieldsOld[i][j];
    }


    public void undo() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field(fieldsOld[i][j]);
            }
        }


    }

    /**
     * Erstellt ein neues Spielbrett mit random Farben
     *
     * @param size     Größe des Spielfeldes
     * @param numColor Anzahl der Farben
     */

    public void drawNewGame(int size, int numColor) {

        this.size = size;
        this.numColor = numColor;

        fields = new Field[size][size];
        fieldsOld = new Field[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field(numColor);
                fieldsOld[i][j] = new Field(fields[i][j]);
            }
        }

        //Verhindere, dass beide startpositionen dieselbe farbe haben
        while (fields[0][0].getColor() == fields[size - 1][size - 1].getColor()) {
            fields[0][0] = new Field(numColor);
        }

    }

    /**
     * Sucht die gewählte Farbe und gibt sie dann zu chechPlanes
     *
     * @param owner Besitzer eines Feldes
     */
    public void zug(int i, int j, int owner) {
        Field f = fields[i][j];
        checkPlanes(f.getColor(), owner);
    }

    /**
     * Fäe
     *
     * @param color
     * @param owner
     */
    protected void checkPlanes(Color color, int owner) {


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fieldsOld[i][j] = new Field(fields[i][j]);
                if (fields[i][j].getOwner() == owner) {
                    fields[i][j].setColor(color);
                }
            }
        }

        boolean found;
        do {
            found = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (fields[i][j].getOwner() == owner) {

                        {
                            if (i > 0) {
                                Field f = fields[i - 1][j];

                                if (f.getOwner() != owner && f.getColor() == color) {
                                    f.setOwner(owner);
                                    found = true;
                                }
                            }
                        }

                        {
                            if (j > 0) {
                                Field f = fields[i][j - 1];

                                if (f.getOwner() != owner && f.getColor() == color) {
                                    f.setOwner(owner);
                                    found = true;
                                }
                            }
                        }

                        {
                            if (i < size - 1) {
                                Field f = fields[i + 1][j];

                                if (f.getOwner() != owner && f.getColor() == color) {
                                    f.setOwner(owner);
                                    found = true;
                                }
                            }
                        }
                        {
                            if (j < size - 1) {
                                Field f = fields[i][j + 1];

                                if (j < size - 1 && f.getOwner() != owner && f.getColor() == color) {
                                    f.setOwner(owner);
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
                if (getField(i, j).getOwner() == player) {
                    score++;
                }
            }
        }
        return score;
    }

}


