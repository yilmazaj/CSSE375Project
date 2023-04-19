package Team7.SettlersOfCatan.Presentation;

import Team7.SettlersOfCatan.Presentation.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntersectionActionListener implements ActionListener {


    private int intersectionIndex;
    private IntersectionButtonManager manager;

    public IntersectionActionListener(int intersectionIndex, IntersectionButtonManager manager){
        this.intersectionIndex = intersectionIndex;
        this.manager = manager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        manager.setSelectedIntersection(intersectionIndex);
    }
}
