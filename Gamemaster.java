package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 06.06.16.;
 */

public class Gamemaster {

    int player = 1;
    int numColors = 4;
    int size = 10;
    int numTurns = 0;
    int numPlayers = 1;
    int claimedP1, claimedP2, highscore;
    boolean end;
    String win = null;
    Color[] playerColor = new Color[numPlayers];

    FloodGameboard F, FOld;

    Player P1, P2;
    Player[] Players;

    /**
     * @param F Spielbrett  bzw Gameboard
     */
    public Gamemaster(FloodGameboard F) {
        this.F = F;
        player = 1;
        newGame();
        end = false;
    }

    /**
     *
     */
    public void newGame() {

        FOld = new FloodGameboard(size, numColors);
        F.drawNewGame(size, numColors);
        Player P1 = new Player(1, size);
        Player P2 = new Player(2, size);
        Players = new Player[numPlayers];
        Players[0] = P1;
        if (numPlayers > 1) {
            Players[1] = P2;
        }

          /* gibt dem Spielern  sein Anfangsfeld */
        for (int i = 0; i < numPlayers; i++) {
                /* takes field with startposition from player*/
            Field f = F.getField(Players[i].getStartPosition(), Players[i].getStartPosition());
            Players[i].setColor(f.getColor());
                /* makes fieldowner*/
            f.setOwner(Players[i].getNumber());

                /*  test if more fields around*/
            F.zug(Players[i].getStartPosition(), Players[i].getStartPosition(), Players[i].getNumber());
        }
    }

    public void turn(int i, int j) {

        if (!end) {
            Color targetColor = F.getField(i, j).getColor();


            /* You must not choose your color or from your opponent*/
            if (targetColor != F.getField(0, 0).getColor()) {

                /** Einzelspieler */
                if (player == 1) {
                    if (Players.length > 1) {
                        if (targetColor != Players[1].getColor()) {
                            F.zug(i, j, Players[0].getNumber());
                            player = 2;
                            numTurns++;
                            claimedP1 = F.getScore(Players[0].getNumber());
                            if (claimedP1 > (size * size) / 2) {
                                win = "Spieler 1 hat gewonnen";
                                end = true;
                            }

                        }
                        /** Erster Spieler bei 2 Spielern*/
                    } else if (Players.length == 1) {
                        F.zug(i, j, Players[0].getNumber());
                        Players[0].setColor(targetColor);
                        numTurns++;
                        claimedP1 = F.getScore(Players[0].getNumber());
                        if (claimedP1 == (size * size)) {
                            win = "Spieler 1 hat gewonnen";
                            end = true;
                        }
                    }

                    /** Zweiter Spieler bei 2 Spielern*/
                } else if (player == 2 && targetColor != Players[1].getColor()) {
                    F.zug(i, j, Players[1].getNumber());
                    Players[1].setColor(targetColor);
                    numTurns++;
                    player = 1;
                    claimedP2 = F.getScore(Players[1].getNumber());
                    if (claimedP2 > (size * size) / 2) {
                        win = "Spieler 2 hat gewonnen";
                        end = true;
                    }
                }
            }
        }
    }

    /**
     * initiert neues Spiel
     *
     * @param size       Größe des Spielfeldes
     * @param numColors  Anzahl der Farben
     * @param numPlayers Anzahl der Spieler
     */
    public void initGame(int size, int numColors, int numPlayers) {
        this.size = size;
        this.numColors = numColors;
        this.numPlayers = numPlayers;
        win = null;
        player = 1;
        numTurns = 0;
        claimedP1 = 0;
        claimedP2 = 0;

        newGame();
    }

    public void undo() {
        F.undo();
    }

    /**
     * gibt ein Feld zurück
     *
     * @param i x-Koordinate
     * @param j y-Koordiante
     * @return ein Feld
     */
    public Field getField(int i, int j) {
        return F.getField(i, j);
    }

    /**
     * @return Gibt die aktuelle Größe des quadratischen Spielfeldes wieder
     */
    public int getSize() {
        return size;
    }

    /**
     * @return Anzahl der Felder von Spieler 1
     */
    public String getClaimedP1() {
        String claim = "Spieler 1 : " + Integer.toString(claimedP1);
        return claim;
    }

    /**
     * @return Anzahl der Ferlder von Spieler 2
     */
    public String getClaimedP2() {
        String claim = "";
        if (numPlayers > 1) {
            claim = "Spieler 2 : " + Integer.toString(claimedP2);
        }
        return claim;
    }

    /**
     * @return String mit Anzahl der gespielten Züge bzw Gewinnbenachrichtugung mit Anzahl der Züge zum Sieg
     */
    public String getNumTurns() {
        String wins;
        if (win != null) {
            wins = win + " in " + numTurns + " Züge.";
        } else {
            String turns = "Züge : " + Integer.toString(numTurns);
            return turns;
        }
        return wins;
    }

    public String getHighscore() {
        String highscoreString = "";
        return highscoreString;
    }


}
