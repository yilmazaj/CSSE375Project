package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorPickerGUI implements ActionListener {
    private JButton button;
    public JFrame frame;

    public Color[] colorArray;
    private int colorsPicked;
    private int numPlayers;

    public boolean isFull;

    public ColorPickerGUI(int numPlayers) {

        colorsPicked = 0;
        isFull = false;
        colorArray = new Color[numPlayers];
        this.numPlayers = numPlayers;

    }

    public void initialize(){
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setTitle("Color Picker");

        button = new JButton("Choose Color");
        button.addActionListener(this);

        frame.add(button);
    }

    public boolean arrayFull(){
        for(int i = 0; i < colorArray.length; i++){
            if(colorArray[i] == null){
                System.out.println("");
                return false;
            }
        }
        return true;
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
            Color selectedColor = JColorChooser.showDialog(null, "Select a color", initialColor);
            if(colorArrayContains(selectedColor)){
                JOptionPane.showMessageDialog(null, "That color has already been selected.");
            } else {
                colorArray[colorsPicked] = selectedColor;
                colorsPicked++;
            }
        }
        if (colorsPicked == numPlayers) {
            isFull = true;
            frame.dispose();
        }
    }
}
