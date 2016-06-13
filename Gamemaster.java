package Gameboard;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * Created by saskia on 06.06.16.boolean finish = false;
 */
public class Gamemaster {

    int player = 1;
    int numColors = 4;
    int size = 10;
    int numTurns = 0;
    int numPlayers = 2;
    int numFields;
    int claimedP1, claimedP2;
    String win = "";
    Color[] playerColor = new Color[numPlayers];

    Flood_Gameboard F;

    Player P1, P2;
    Player[] Players;


    public Gamemaster(Flood_Gameboard F) {
        this.F = F;
        player = 1;
        newGame();
    }


    public void newGame() {
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
                /* makes fieldowner*/
            f.owner = Players[i].number;
                /*  test if more fields around*/
            F.zug(Players[i].getStartPosition(), Players[i].getStartPosition(), Players[i].number);
        }
    }

    public void turn(int i, int j) {


        Color targetColor = F.getField(i, j).color;


        /* You must not choose your color or from your opponent*/
        if (targetColor != F.getField(0, 0).color) {


            if (player == 1) {
                if (Players.length > 1) {
                    player = 2;
                    if (targetColor != Players[1].color) {
                        F.zug(i, j, Players[0].number);
                        claimedP1 = F.getScore(Players[0].number);
                        if (claimedP1 > (size * size) / 2) {
                            win = "Spieler 1 hat gewonnen";
                        }
                    }
                } else {
                    F.zug(i, j, Players[0].number);
                    claimedP1 = F.getScore(Players[0].number);
                    if (claimedP1 == (size * size)) {
                        win = "Spieler 1 hat gewonnen";
                    }
                }

            } else if (player == 2 && targetColor != Players[1].color) {
                F.zug(i, j, Players[1].number);
                player = 1;
                claimedP2 = F.getScore(Players[1].number);
                if (claimedP2 > (size * size) / 2) {
                    win = "Spieler 2 hat gewonnen";

                }
            }
        }
    }

    public void initGame(int size) {

        Flood_Gameboard F = new Flood_Gameboard(size, numColors);


    }

    public Field getField(int i, int j) {
        return F.getField(i, j);
    }

    public void setSize(int i) {
        this.size = i;
    }

    public int getsize() {
        return size;
    }

    public String getClaimedP1(){
        String claim = "Spieler 1 : " + Integer.toString(claimedP1);
        return claim;
    }

    public String getClaimedP2(){
        String claim = "";
        if (numPlayers > 1 ) {
            claim = "Spieler 2 : " + Integer.toString(claimedP2);
        }
        return claim;
    }

    public String getWin(){
        return win;
    }

}
