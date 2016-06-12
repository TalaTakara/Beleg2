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


    Draw_Frame(Gamemaster M){
        this.M = M;


        JFrame frame = new JFrame();
        setTitle("Flood it");
        setSize(600, 600);
        setLocation(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);



        add(bu1 = new JButton("new Game"));
        bu1.setBounds(450, 30, 100, 50);
        bu1.addActionListener( al );


        add(slider = new JSlider(3, 6, 4));
        slider.setBounds(100, 10, 300,50);
        slider.setPaintTicks(true);    //Striche werden angezeigt
        slider.setPaintLabels(true);  //Zahlen werden nicht angezeigt
        slider.setMinimum(2);    //stellt den Minimalwert auf 0 ein
        slider.setMaximum(6);  //stellt den Maximalwert auf 150 ein
        slider.setValue(4);     //selektiert den Wert 68
        slider.setMajorTickSpacing(1); //Abstände im Großraster

        addMouseListener(new mouseclick());
        frame.pack();
        setVisible(true);

    }

    ActionListener al = new ActionListener() {
        @Override public void actionPerformed( ActionEvent e ) {
           int size = slider.getValue();
                M.initGame(size);
            repaint();
        }
    };


    public void paint(Graphics g) {
        super.paintComponents(g);
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

            M.turn(i,j);
            /* Debug which Field is clicked */
//            System.out.print("sn" + i);
//            System.out.println("zn" + j);

            repaint();
        }
    }

}

//     class RadioButton extends JPanel implements ActionListener {
//    static String birdString = "Bird";
//    static String catString = "Cat";
//    JLabel picture;
//
//    public RadioButtonDemo() {
//        super(new BorderLayout());
//
//        //Create the radio buttons.
//        JRadioButton birdButton = new JRadioButton(birdString);
//        birdButton.setMnemonic(KeyEvent.VK_B);
//        birdButton.setActionCommand(birdString);
//        birdButton.setSelected(true);
//
//        JRadioButton catButton = new JRadioButton(catString);
//        catButton.setMnemonic(KeyEvent.VK_C);
//        catButton.setActionCommand(catString);
//
//        ButtonGroup group = new ButtonGroup();
//        group.add(birdButton);
//        group.add(catButton);
//
//        //Register a listener for the radio buttons.
//        birdButton.addActionListener(this);
//        catButton.addActionListener(this);
//
//        //Set up the picture label.
//        picture = new JLabel(createImageIcon("images/"
//                + birdString
//                + ".gif"));
//
//        //The preferred size is hard-coded to be the width of the
//        //widest image and the height of the tallest image.
//        //A real program would compute this.
//        picture.setPreferredSize(new Dimension(177, 122));
//
//
//        //Put the radio buttons in a column in a panel.
//        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
//        radioPanel.add(birdButton);
//        radioPanel.add(catButton);
//
//        add(radioPanel, BorderLayout.LINE_START);
//        add(picture, BorderLayout.CENTER);
//        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//    }
//
//
//    /**
//     * Listens to the radio buttons.
//     */
//    public void actionPerformed(ActionEvent e) {
//        picture.setIcon(createImageIcon("images/"
//                + e.getActionCommand()
//                + ".gif"));
//    }
//
//    /**
//     * Returns an ImageIcon, or null if the path was invalid.
//     */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = RadioButtonDemo.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
//}

// // TODO: 06.06.16 Frame adden um slider gängig zu machen
//// TODO: 07.06.16 Spielerfeldzahl und gewinnbedingungen anzeigen ... Züge anzeigen (nur noch anzeigen)
