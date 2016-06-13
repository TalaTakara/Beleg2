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
    JSlider slider;
    final JRadioButton onePButton;
    final JRadioButton twoPButton;
    ButtonGroup group;

    private JFrame mainFrame;
    private JLabel highscore, PunkteP1, PunkteP2, win;
    private JLabel statusLabel;
    private JPanel controlPanel;


    Draw_Frame(Gamemaster M){
        this.M = M;

        mainFrame = new JFrame();
        setTitle("Flood it");
        setSize(600, 600);
        setLocation(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        PunkteP1 = new JLabel(  M.getClaimedP1());
       PunkteP1.setBounds(100,450,150,50);
        add(PunkteP1);


        PunkteP2 = new JLabel(   M.getClaimedP2());
        PunkteP2.setBounds(300,450,150,50);
        add(PunkteP2);

        win = new JLabel(M.getWin());
        win.setBounds(200,410,200,50);
        add(win);

        add(bu1 = new JButton("new Game"));
        bu1.setBounds(450, 30, 100, 50);
        bu1.addActionListener(al_newGame);

        add(slider = new JSlider(3, 6, 4));
        slider.setBounds(100, 10, 300,50);
        slider.setPaintTicks(true);    //Striche werden angezeigt
        slider.setPaintLabels(true);  //Zahlen werden nicht angezeigt
        slider.setMinimum(2);    //stellt den Minimalwert auf 0 ein
        slider.setMaximum(6);  //stellt den Maximalwert auf 150 ein
        slider.setValue(4);     //selektiert den Wert 68
        slider.setMajorTickSpacing(1); //Abstände im Großraster


        onePButton = new JRadioButton(" 1 Player",true);
//        birdButton.setSelected(true);

        twoPButton = new JRadioButton("2 Player");

        onePButton.setMnemonic(KeyEvent.VK_C);
        twoPButton.setMnemonic(KeyEvent.VK_M);

        onePButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                statusLabel.setText("Apple RadioButton: "
                        + (e.getStateChange()==1?"checked":"unchecked"));
            }
        });

        group = new ButtonGroup();
        group.add(onePButton);
        group.add(twoPButton);




//        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());


        addMouseListener(new mouseclick());
        mainFrame.pack();
//        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);

        setVisible(true);

    }


    ActionListener al_newGame = new ActionListener() {
        @Override public void actionPerformed( ActionEvent e ) {
           int size = slider.getValue();
                M.initGame(size);
            repaint();
        }
    };


    public void paint(Graphics g) {
        super.paintComponents(g);

        PunkteP1.setText( M.getClaimedP1());
        PunkteP2.setText( M.getClaimedP2());
        win.setText(M.getWin());


        for (int i = 0; i < M.getsize(); i++) {
            for (int j = 0; j < M.getsize(); j++) {
                Field f = M.getField(i,j);
               Color c =f.color;

                g.setColor(c);
                g.fillRect(100 + i * 30, 100 + j * 30, 30, 30);
                g.setColor(Color.black);
                g.drawRect(100 + i * 30, 100 + j * 30, 30, 30);

                /* Debugger for Ownership */
                if ( f.owner != 0){
                   g.setFont(new Font("Sans", Font.BOLD, 20));
                      g.drawString( "" + f.owner ,110+ i * 30, 125 + j * 30);
                }
            }
        }
    }




    class mouseclick extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            int x, y, i , j;
            x = e.getX();
            y = e.getY();

            i = ((x -100)/30);
            j = ((y -100)/30);

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
