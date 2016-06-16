package Gameboard;

/**
 * Created by saskia on 07.06.16.
 */

import java.awt.*;

public class Player {


    private int number;
    private int size;
    private Color color;


    public Player(int number, int size) {
        this.number = number;
        this.size = size;

    }

    public int getNumber() {
        return number;
    }

    public int getStartPosition() {
        if (number == 1) {
            return 0;
        } else {
            return size - 1;
        }
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
