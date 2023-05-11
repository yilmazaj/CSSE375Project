package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorPickerGUI extends JFrame implements ActionListener {
    private JButton button;
    public JFrame frame;

    private Color[] colorArray;
    private int colorsPicked;
    private int numPlayers;

    public ColorPickerGUI(int numPlayers) {

        colorsPicked = 0;

        colorArray = new Color[numPlayers];
        this.numPlayers = numPlayers;

        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setTitle("Color Picker");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        button = new JButton("Choose Color");
        button.addActionListener(this);

        frame.add(button);
    }

    public boolean colorArrayContains(Color color){
        for(int i = 0; i < colorArray.length; i++){
            if(color.equals(colorArray[i])){
                return true;
            }
        }
        return false;
    }

    public Color[] getColorArray(){
        return colorArray;
    }

    public void actionPerformed(ActionEvent e) {
        if (colorsPicked < numPlayers) {
            Color initialColor = Color.RED;
            Color selectedColor = JColorChooser.showDialog(this, "Select a color", initialColor);
            if(colorArrayContains(selectedColor)){
                JOptionPane.showMessageDialog(null, "That color has already been selected.");
            } else {
                colorArray[colorsPicked] = selectedColor;
                colorsPicked++;
            }
        }
        if (colorsPicked == numPlayers) {
            frame.dispose();
        }
    }
}
