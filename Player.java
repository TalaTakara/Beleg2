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
    Color color;



    public Player(int number,int size){
        this.number = number;
        this.size = size;
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
}
