package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 05.06.16.
 */
public class Field {

    public Color color;
    public int owner;
    public int numColor;

    public Field(int numColor){

        this.numColor = numColor;
        color = PALETTE[ (int) (Math.random() * numColor)];
        owner = 0;
    }


    public static final Color MAGENTA = new Color(226, 0, 116);
    public static final Color PURPLE = new Color(138, 43, 226);
    public static final Color BLUE = new Color(0, 0, 250);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color YELLOW = new Color(255, 215, 0);
    public static final Color RED = new Color(255, 69, 0);


    public static final Color[] PALETTE = {MAGENTA, BLUE, GREEN,YELLOW, PURPLE, RED};

}