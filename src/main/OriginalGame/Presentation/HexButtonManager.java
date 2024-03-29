package Presentation;

import javax.swing.*;
import java.util.ArrayList;

public class HexButtonManager {

    private ArrayList<JButton> hexButtons;

    private int selectedHex;

    public HexButtonManager(){
        hexButtons = new ArrayList<>();
        selectedHex = -1;
    }

    public void enableHexButtons(boolean setTo){
        for(JButton hexButton : hexButtons){
            hexButton.setVisible(setTo);
            hexButton.setEnabled(setTo);
        }
    }

    public JButton createHexButton(HexPoint point, int id){
        JButton hexButton = new HexButton("");
        hexButton.setBounds((int) (point.point.getX()-38),(int) (point.point.getY()-40),80,80);
        hexButton.setContentAreaFilled(false);

        hexButton.setFocusPainted( false );
        hexButton.setVisible(false);
        HexActionListener listener = new HexActionListener(id, this);
        hexButton.addActionListener(listener);
        hexButtons.add(hexButton);
        return hexButton;
    }


    public void setSelectedHex(int selected){
        selectedHex = selected;
    }

    public int getSelectedHex(){
        int temp = selectedHex;
        selectedHex = -1;
        return temp;
    }


}
