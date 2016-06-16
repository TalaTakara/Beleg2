package Gameboard;

import java.awt.*;

/**
 * Created by saskia on 05.06.16.
 */
public class Field {

    private Color color;
    private int owner;
    private int numColor;

    public int getOwner() {
        return owner;
    }

    public Color getColor() {
        return color;
    }

    Field(int numColor) {
        this.numColor = numColor;
        color = PALETTE[(int) (Math.random() * numColor)];
        owner = 0;
    }

    Field(Field other) {
        this.numColor = other.numColor;
        this.owner = other.owner;
        this.color = other.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    private static final Color MAGENTA = new Color(226, 0, 116);
    private static final Color PURPLE = new Color(138, 43, 226);
    private static final Color BLUE = new Color(0, 0, 250);
    private static final Color GREEN = new Color(0, 255, 0);
    private static final Color YELLOW = new Color(255, 215, 0);
    private static final Color RED = new Color(255, 69, 0);


    private static final Color[] PALETTE = {MAGENTA, BLUE, GREEN, YELLOW, PURPLE, RED};


}