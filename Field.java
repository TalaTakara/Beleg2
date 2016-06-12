package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 05.06.16.
 */
public class Field {

    public Color color;
    public int owner;

    public Field(){
        color = palette[ (int) (Math.random() * palette.length)];
        owner = 0;
    }


    public static final Color PURPLE = new Color(155, 95, 170);
    public static final Color PINK = new Color(237, 112, 161);
    public static final Color BLUE = new Color(70, 177, 226);
    public static final Color GREEN = new Color(126, 157, 30);
    public static final Color YELLOW = new Color(243, 246, 29);
    public static final Color RED = new Color(220, 74, 32);

    public static final Color[] palette = {PURPLE, PINK, BLUE, GREEN, YELLOW, RED};

}