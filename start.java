package Gameboard;

import Gameboard.Draw_Frame;
import Gameboard.Gamemaster;

/**
 * Created by saskia on 30.05.16.
 */
public class start {

    public static void main(String args[]){

        Flood_Gameboard F = new Flood_Gameboard(10, 4);
        Gamemaster M = new Gamemaster(F);

        Draw_Frame D = new Draw_Frame(M);

    }
}
