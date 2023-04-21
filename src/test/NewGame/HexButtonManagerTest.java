import Presentation.HexButtonManager;
import Presentation.HexPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexButtonManagerTest {

    private HexButtonManager manager;

    private ArrayList<JButton> hexButtons;

    @BeforeEach
    public void setup(){
        manager = new HexButtonManager();
        hexButtons = new ArrayList<>();
        setupHexButtons();
    }

    private void setupHexButtons(){
        for(int i = 0; i < 20; i++){
            HexPoint point = new HexPoint(new Point2D.Double(1.0,1.0));
            JButton hexButton = manager.createHexButton(point, i);
            hexButtons.add(hexButton);
        }
    }

    @Test
    public void enableHexButtons(){
        manager.enableHexButtons(true);

        for(int i = 0; i < hexButtons.size(); i++){
            JButton currentButtonToTest = hexButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }
    }

    @Test
    public void disableHexButtons(){
        manager.enableHexButtons(false);

        for(int i = 0; i < hexButtons.size(); i++){
            JButton currentButtonToTest = hexButtons.get(i);
            assertFalse(currentButtonToTest.isEnabled());
        }
    }

    @Test
    public void hexButtonsDefaultValue(){
        for(int i = 0; i < hexButtons.size(); i++){
            JButton currentButtonToTest = hexButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }
    }

    @Test
    public void getSelectedHex1(){
        manager.enableHexButtons(true);
        hexButtons.get(0).doClick();
        assertEquals(0,manager.getSelectedHex());
    }

    @Test
    public void getSelectedHex2(){
        manager.enableHexButtons(true);
        assertEquals(-1,manager.getSelectedHex());
    }

    @Test
    public void getSelectedHex3(){
        manager.enableHexButtons(true);
        hexButtons.get(0).doClick();
        assertEquals(0,manager.getSelectedHex());
        assertEquals(-1,manager.getSelectedHex());
    }
}
