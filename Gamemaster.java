package Gameboard;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * Created by saskia on 06.06.16.
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
    Draw_Frame D;

    Player P1,P2;
    Player[] Players;


    public Gamemaster( Flood_Gameboard F) {
        this.F = F;
        player = 1;
        newGame();
    }



    public void newGame() {
        Player P1 = new Player(1,size);
        Player P2 = new Player(2,size);
        Player[] Players = new Player[numPlayers];
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

    public void  turn(int i, int j){

        Color targetColor = F.getField(i,j).color;

        /* You must not choose your color or from your opponent*/
        if ( targetColor != F.getField(0, 0).color && targetColor != F.getField(size-1,size-1).color){

                F.zug(i,j,player);
                numTurns++;
//                Players[player-1].color = targetColor;
            playerColor[player-1] = targetColor;
            player++;
                if ( player > numPlayers)
                    player = 1;
}
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

    public void setNumColors(int i){
        this.numColors = i;
        Flood_Gameboard F = new Flood_Gameboard(size,numColors);

    }

    public void setNumPlayers(int i){
        this.numPlayers = i;
        Flood_Gameboard F = new Flood_Gameboard(size,numColors);

    }
}
