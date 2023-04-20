package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HexActionListener implements ActionListener {


    private int hexIndex;
    private HexButtonManager manager;

    public HexActionListener(int hexIndex, HexButtonManager manager){
        this.hexIndex = hexIndex;
        this.manager = manager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        manager.setSelectedHex(hexIndex);
    }
}
