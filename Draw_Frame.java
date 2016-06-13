package Gameboard; /**
 * Created by saskia on 30.05.16.
 */

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;


public class Draw_Frame extends JFrame {

    Flood_Gameboard F;
    Gamemaster M ;
    JButton bu1, bu2;
    JSlider slider_color, slider_size, slider_player;
    int fieldsize = 30;

    private JFrame mainFrame;
    private JLabel highscore, PunkteP1, PunkteP2, turns_win, num_Colors, num_Players, labelSize, labelTurns;


    Draw_Frame(Gamemaster M){
        this.M = M;

        mainFrame = new JFrame();
        setTitle("Flood it");
        setSize(600, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        PunkteP1 = new JLabel(  M.getClaimedP1());
        PunkteP1.setBounds(100,100,150,50);
        add(PunkteP1);

        PunkteP2 = new JLabel(   M.getClaimedP2());
        PunkteP2.setBounds(300,100,150,50);
        add(PunkteP2);

        turns_win = new JLabel(M.getnumTurns());
        turns_win.setBounds(100,75,400,50);
        add(turns_win);


        add(bu1 = new JButton("new Game"));
        bu1.setBounds(450, 80, 100, 50);
        bu1.addActionListener(al_newGame);


        labelSize = new JLabel( "Felder");
        labelSize.setBounds(10,0,50,50);
        add(labelSize);

        slider_size = new JSlider(SwingConstants.VERTICAL, 5, 30, 10 );
        slider_size.setBounds(0, 50, 50,500);
        slider_size.setPaintTicks(true);    //Striche werden angezeigt
        slider_size.setPaintLabels(true);  //Zahlen werden angezeigt
        slider_size.setMajorTickSpacing(5); //Abstände im Großraster
        slider_size.setSnapToTicks(true);
        add(slider_size);

        num_Players = new JLabel( "Spieleranzahl");
        num_Players.setBounds(450,0,150,30);
        add(num_Players);

        slider_player = new JSlider( 1, 2, 1 );
        slider_player.setBounds(450, 20, 100 ,50);
        slider_player.setPaintTicks(true);    //Striche werden angezeigt
        slider_player.setPaintLabels(true);  //Zahlen werden angezeigt
        slider_player.setMajorTickSpacing(1); //Abstände im Großraster
        add(slider_player);


        num_Colors = new JLabel( "Anzahl der Farben");
        num_Colors.setBounds(200,0,150,30);
        add(num_Colors);

        slider_color = new JSlider(3, 6, 4);
        slider_color.setBounds(100, 20, 300,50);
        slider_color.setPaintTicks(true);    //Striche werden angezeigt
        slider_color.setPaintLabels(true);  //Zahlen werden angezeigt
        slider_color.setMajorTickSpacing(1); //Abstände im Großraster
        add(slider_color);

        addMouseListener(new mouseclick());
        mainFrame.pack();
        setVisible(true);

    }


    ActionListener al_newGame = new ActionListener() {
        @Override public void actionPerformed( ActionEvent e ) {
           int numColors = slider_color.getValue();
            int numPlayer = slider_player.getValue();
            int size = slider_size.getValue();
            if (size > 14){
                setSize(750,750);
            }
            if (size > 19){
                fieldsize = 15;
            }
                M.initGame(size, numColors, numPlayer);
            repaint();
        }
    };



    public void paint(Graphics g) {
        super.paintComponents(g);

        PunkteP1.setText( M.getClaimedP1());
        PunkteP2.setText( M.getClaimedP2());
        turns_win.setText(M.getnumTurns());


        for (int i = 0; i < M.getsize(); i++) {
            for (int j = 0; j < M.getsize(); j++) {
                Field f = M.getField(i,j);
               Color c =f.color;

                g.setColor(c);
                g.fillRect(100 + i * fieldsize, 150 + j * fieldsize, fieldsize, fieldsize);
                g.setColor(Color.black);
                g.drawRect(100 + i * fieldsize, 150 + j * fieldsize, fieldsize, fieldsize);

                /* Debugger for Ownership */
                if ( f.owner != 0){
                   g.setFont(new Font("Sans", Font.BOLD, 20));
                      g.drawString( "" + f.owner ,110+ i * 30, 175 + j * 30);
                }
            }
        }
    }




    class mouseclick extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int x, y, i , j;
            x = e.getX();
            y = e.getY();

            i = ((x -100)/fieldsize);
            j = ((y -150)/fieldsize);

            if (i >= 0 && i < M.getsize() && j >=  0 && j < M.getsize()) {
                M.turn(i, j);
            /* Debug which Field is clicked */
//            System.out.print("sn" + i);
//            System.out.println("zn" + j);

                repaint();
            }
        }
    }

}



// // TODO: 06.06.16 Frame adden um slider gängig zu machen
//// TODO: 07.06.16 Spielerfeldzahl und gewinnbedingungen anzeigen ... Züge anzeigen (nur noch anzeigen)
