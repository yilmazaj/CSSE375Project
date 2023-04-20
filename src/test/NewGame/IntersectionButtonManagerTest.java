import Presentation.IntersectionButtonManager;
import Presentation.IntersectionPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntersectionButtonManagerTest {

    private IntersectionButtonManager manager;

    private ArrayList<JButton> intersectionButtons;

    @BeforeEach
    public void setup(){
        manager = new IntersectionButtonManager();
        intersectionButtons = new ArrayList<>();
        setupIntersectionButtons();
    }

    private void setupIntersectionButtons(){
        for(int i = 0; i < 20; i++){
            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(1.0,1.0));
            JButton intersectionButton = manager.createIntersectionButton(point, i);
            intersectionButtons.add(intersectionButton);
        }
    }

    @Test
    public void enableIntersectionButtons(){
        manager.enableIntersectionButtons(true);

        for(int i = 0; i < intersectionButtons.size(); i++){
            JButton currentButtonToTest = intersectionButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }

    }

    @Test
    public void disableIntersectionButtons(){
        manager.enableIntersectionButtons(false);

        for(int i = 0; i < intersectionButtons.size(); i++){
            JButton currentButtonToTest = intersectionButtons.get(i);
            assertFalse(currentButtonToTest.isEnabled());
        }

    }

    @Test
    public void intersectionButtonsDefaultValue(){
        for(int i = 0; i < intersectionButtons.size(); i++){
            JButton currentButtonToTest = intersectionButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }
    }

    @Test
    public void getSelectedIntersection1(){
        manager.enableIntersectionButtons(true);

        intersectionButtons.get(0).doClick();

        assertEquals(0,manager.getSelectedIntersection());

    }

    @Test
    public void getSelectedIntersection2(){
        manager.enableIntersectionButtons(true);

        assertEquals(-1,manager.getSelectedIntersection());
    }

    @Test
    public void getSelectedIntersection3(){
        manager.enableIntersectionButtons(true);

        intersectionButtons.get(0).doClick();

        assertEquals(0,manager.getSelectedIntersection());

        assertEquals(-1,manager.getSelectedIntersection());
    }
}
