package Gameboard; /**
 * Created by saskia on 30.05.16.
 */

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;


public class DrawFrame extends JFrame {

    FloodGameboard F;
    Gamemaster M;
    JButton bu1, bu2;
    JSlider sliderColor, sliderSize, sliderPlayer;
    int fieldsize = 30;

    private JFrame mainFrame;
    private JLabel highscore, PunkteP1, PunkteP2, turnsWin, num_Colors, num_Players, labelSize;


    DrawFrame(Gamemaster M) {
        this.M = M;

        mainFrame = new JFrame();
        setTitle("Flood it");
        setSize(600, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        PunkteP1 = new JLabel(M.getClaimedP1());
        PunkteP1.setBounds(100, 100, 150, 50);
        add(PunkteP1);

        PunkteP2 = new JLabel(M.getClaimedP2());
        PunkteP2.setBounds(300, 100, 150, 50);
        add(PunkteP2);

        turnsWin = new JLabel(M.getNumTurns());
        turnsWin.setBounds(100, 75, 400, 50);
        add(turnsWin);


        add(bu1 = new JButton("new Game"));
        bu1.setBounds(450, 80, 100, 50);
        bu1.addActionListener(alNewGame);


        labelSize = new JLabel("Felder");
        labelSize.setBounds(10, 0, 50, 50);
        add(labelSize);

        sliderSize = new JSlider(SwingConstants.VERTICAL, 5, 30, 10);
        sliderSize.setBounds(0, 50, 50, 500);
        sliderSize.setPaintTicks(true);    //Striche werden angezeigt
        sliderSize.setPaintLabels(true);  //Zahlen werden angezeigt
        sliderSize.setMajorTickSpacing(5); //Abstände im Großraster
        sliderSize.setSnapToTicks(true);
        add(sliderSize);

        num_Players = new JLabel("Spieleranzahl");
        num_Players.setBounds(450, 0, 150, 30);
        add(num_Players);

        sliderPlayer = new JSlider(1, 2, 1);
        sliderPlayer.setBounds(450, 20, 100, 50);
        sliderPlayer.setPaintTicks(true);    //Striche werden angezeigt
        sliderPlayer.setPaintLabels(true);  //Zahlen werden angezeigt
        sliderPlayer.setMajorTickSpacing(1); //Abstände im Großraster
        add(sliderPlayer);


        num_Colors = new JLabel("Anzahl der Farben");
        num_Colors.setBounds(200, 0, 150, 30);
        add(num_Colors);

        sliderColor = new JSlider(3, 6, 4);
        sliderColor.setBounds(100, 20, 300, 50);
        sliderColor.setPaintTicks(true);    //Striche werden angezeigt
        sliderColor.setPaintLabels(true);  //Zahlen werden angezeigt
        sliderColor.setMajorTickSpacing(1); //Abstände im Großraster
        add(sliderColor);

        addMouseListener(new mouseclick());
        mainFrame.pack();
        setVisible(true);

    }


    ActionListener alNewGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int numColors = sliderColor.getValue();
            int numPlayer = sliderPlayer.getValue();
            int size = sliderSize.getValue();
            if (size > 14) {
                setSize(750, 750);
            }
            if (size > 19) {
                fieldsize = 25;
            }
            if (size > 24){
                fieldsize = 20;
            }
            M.initGame(size, numColors, numPlayer);
            repaint();
        }
    };


    public void paint(Graphics g) {
        super.paintComponents(g);

        for (int i = 0; i < M.getSize(); i++) {
            for (int j = 0; j < M.getSize(); j++) {
                Field f = M.getField(i, j);
                Color c = f.color;

                g.setColor(c);
                g.fillRect(100 + i * fieldsize, 150 + j * fieldsize, fieldsize, fieldsize);
                g.setColor(Color.black);
                g.drawRect(100 + i * fieldsize, 150 + j * fieldsize, fieldsize, fieldsize);

                /* Debugger for Ownership */
                if (f.owner != 0) {
                    g.setFont(new Font("Sans", Font.BOLD, 20));
                    g.drawString("" + f.owner, 110 + i * 30, 175 + j * 30);
                }
            }
        }
    }


    class mouseclick extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int x, y, i, j;
            x = e.getX();
            y = e.getY();

            i = ((x - 100) / fieldsize);
            j = ((y - 150) / fieldsize);

            if (i >= 0 && i < M.getSize() && j >= 0 && j < M.getSize()) {
                M.turn(i, j);
            /* Debug which Field is clicked */
//            System.out.print("sn" + i);
//            System.out.println("zn" + j);

                PunkteP1.setText(M.getClaimedP1());
                PunkteP2.setText(M.getClaimedP2());
                turnsWin.setText(M.getNumTurns());

                repaint();
            }
        }
    }

}


// // TODO: 06.06.16 Frame adden
