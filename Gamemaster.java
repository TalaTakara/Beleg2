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
    int numTurns=0;
    int numPlayers = 2;
    int numFields;
    Color[] playerColor = new Color[numPlayers];

    Flood_Gameboard F;

    Player P1,P2;
    Player[] Players;


    public Gamemaster( Flood_Gameboard F) {
        this.F = F;
        player = 1;
        newGame();
    }



    public void newGame() {
        Player P1 = new Player(1,size,true);
        Player P2 = new Player(2,size,false);
        Players = new Player[numPlayers];
        Players[0] = P1;
        if ( numPlayers > 1 ) { Players[1] = P2;}


          /* gibt dem Spielern  sein Anfangsfeld */
        for (int i = 0; i < numPlayers;i++ ){
                /* takes field with startposition from player*/
                Field f = F.getField(Players[i].getStartPosition(), Players[i].getStartPosition());
                /* makes fieldowner*/
                f.owner = Players[i].number;
                /*  test if more fields around*/
                F.zug(Players[i].getStartPosition(), Players[i].getStartPosition(), Players[i].number);
        }
        F.check_planes(P1.color,P1.number);
        if (numPlayers > 1 ){F.check_planes(P2.color,P2.number);}
    }

    public void  turn(int i, int j) {
//        F.zug(i, j, player);
//        F.zug(i, j, player);
//        numTurns++;
//        playerColor[player - 1] = targetColor;
//        player++;
//        if (player > numPlayers)
//
//            numTurns++;
//        playerColor[player - 1] = targetColor;
//        player++;
//        if (player > numPlayers)

            Color targetColor = F.getField(i, j).color;
        int claimed;

        /* You must not choose your color or from your opponent*/
        if (targetColor != F.getField(0, 0).color && targetColor != F.getField(size - 1, size - 1).color) {

            if (Players[0].getTurn()) {
                F.zug(i, j, Players[0].number);
                if (Players.length > 1) {
                    Players[1].getTurn();
                    claimed = F.getScore(Players[0].number);
                    if (claimed == (size * size) / 2) {
                        System.out.print("win player 1");
                    }
                }
            }
                    } else if ( Players[1].getTurn()) {
                        F.zug(i, j, Players[1].number);
                        claimed = F.getScore(Players[1].number);
                        if (claimed == (size * size) / 2) {
                            System.out.print("win player 2");

                        }


//            F.zug(i,j,player);
//            numTurns++;
//            playerColor[player-1] = targetColor;
//            player++;
//                if ( player > numPlayers)
//                    player = 1;
                    }

//            int claimed = F.getScore(player);
//            if (claimed == (size * size) / 2) {
//                System.out.print("win");



    }
    public void initGame(int size){

        Flood_Gameboard F = new Flood_Gameboard(size, numColors);


    }

    public Field getField(int i, int j){
      return   F.getField(i,j);
    }

    public void setSize(int i){
        this.size = i;
    }

    public int getsize() {
        return size;
    }


}
