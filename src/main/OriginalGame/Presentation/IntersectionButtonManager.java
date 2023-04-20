package Presentation;

import javax.swing.*;
import java.util.ArrayList;

public class IntersectionButtonManager {

    private ArrayList<JButton> intersectionButtons;

    private int selectedIntersection;

    public IntersectionButtonManager(){
        intersectionButtons = new ArrayList<>();
        selectedIntersection = -1;
    }

    public void enableIntersectionButtons(boolean setTo){
        for(JButton intersectionButton : intersectionButtons){
            intersectionButton.setVisible(setTo);
            intersectionButton.setEnabled(setTo);
        }
    }

    public JButton createIntersectionButton(IntersectionPoint point, int id){
        JButton intersectionButton = new JButton("");
        intersectionButton.setBounds((int) (point.point.getX()-10),(int) (point.point.getY()-10),20,20);
        intersectionButton.setVisible(true);
        intersectionButton.setContentAreaFilled(false);
        IntersectionActionListener listener = new IntersectionActionListener(id, this);
        intersectionButton.addActionListener(listener);
        intersectionButtons.add(intersectionButton);
        return intersectionButton;
    }


    public void setSelectedIntersection(int selected){
        selectedIntersection = selected;
    }

    public int getSelectedIntersection(){
        int temp = selectedIntersection;
        selectedIntersection = -1;
        return temp;
    }


}
