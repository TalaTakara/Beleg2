package Gameboard;

/**
 * Created by saskia on 30.05.16.
 */
public class start {

    public static void main(String args[]){

        FloodGameboard F = new FloodGameboard(10, 4);
        Gamemaster M = new Gamemaster(F);

        DrawFrame D = new DrawFrame(M);

    }
}
