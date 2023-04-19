package Team7.SettlersOfCatan.Presentation;

import Team7.SettlersOfCatan.Presentation.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntersectionActionListener implements ActionListener {


    private int intersectionIndex;
    private GameBoard board;

    public IntersectionActionListener(int intersectionIndex, GameBoard board){
        this.intersectionIndex = intersectionIndex;
        this.board = board;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        board.setSelectedIntersection(intersectionIndex);
    }
}
