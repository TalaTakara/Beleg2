package Gameboard;

/**
 * Created by saskia on 07.06.16.
 */

import  java.awt.*;
public class Player {

    int number;
    int highscore;
    int score;
    int size;
    boolean turn;
    Color color;




    public Player(int number,int size, boolean turn){
        this.number = number;
        this.size = size;
        this.turn = turn;
        highscore = 0;
        score = 0;

    }

    public int getStartPosition(){
        if (number ==1 ){
            return  0;
        }
        else {
            return size-1;
        }
    }

    public boolean getTurn(){
        if (turn  == true){
            turn = false;
            return true;
        }
        else {
            turn = true;
            return false;
        }
    }


}
