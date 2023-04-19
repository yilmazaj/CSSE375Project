import Team7.SettlersOfCatan.*;
import Team7.SettlersOfCatan.Presentation.GameBoard;
import Team7.SettlersOfCatan.Presentation.GraphicsWithIndex;
import Team7.SettlersOfCatan.Presentation.HexagonData;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class NewGameBoardTest {

    private GameBoard gb;

    @Before
    public void setup(){ //ANDREW REFACTOR
        GameBoard gb = new GameBoard(1);
    }

    @Test
    public void paintComponentTest(){
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        f.add(panel);
        f.setVisible(true);
        Graphics g = f.getGraphics(); //Easiest way to get 2D graphics implementation to test

        gb.paintComponent(g);

        int size = 54;
        assertEquals(g.getColor(),gb.intersectionPoints[size-1].color);
        assertEquals(gb.startingPoint, new Point2D.Double(100, 100));
        g.create();

    }

    private Graphics2D initGraphics2D(){
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        return image.createGraphics();
    }

    @Test
    public void testDrawHex(){
        
        double x = 0.0;
        double y = 0.0;
        Hex testHex = new NoResourceHex(-1);
        Graphics2D g2 = initGraphics2D();
        Point2D.Double point = new Point2D.Double(x, y);
        assertEquals(gb.drawHex(testHex, g2, point).getXCenter(), x);
        assertEquals(gb.drawHex(testHex, g2, point).getYCenter(), y);
    }

    @Test
    public void drawHexShapeTest(){
        HexagonData hdata = new HexagonData(1, 1, new Polygon());
        Graphics2D g2 = initGraphics2D();
        hdata.drawHexShape(g2);
        assertEquals(g2.getColor(), new Color(51, 62, 79));
        assertEquals(g2.getStroke(), new BasicStroke(5));
    }

    @Test
    public void drawHexNumberAtPositionTest(){
        double x = 0.0;
        double y = 0.0;
        Graphics2D g2 = initGraphics2D();
        
        GraphicsWithIndex gwi = new GraphicsWithIndex(g2, 1);
        gb.drawHexNumberAtPosition(gwi, new Point2D.Double(x, y));
        assertEquals(g2.getColor(), new Color(51,62,79));
        assertEquals(g2.getStroke(), new BasicStroke(10));
    }

    @Test
    public void enableIntersectionButtons(){
        
        gb.enableIntersectionButtons(true);

        for(int i = 0; i < gb.intersectionButtons.size(); i++){
            JButton currentButtonToTest = gb.intersectionButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }

    }

    @Test
    public void disableIntersectionButtons(){
        
        gb.enableIntersectionButtons(false);

        for(int i = 0; i < gb.intersectionButtons.size(); i++){
            JButton currentButtonToTest = gb.intersectionButtons.get(i);
            assertFalse(currentButtonToTest.isEnabled());
        }

    }

    @Test
    public void intersectionButtonsDefaultValue(){
        

        for(int i = 0; i < gb.intersectionButtons.size(); i++){
            JButton currentButtonToTest = gb.intersectionButtons.get(i);
            assertTrue(currentButtonToTest.isEnabled());
        }
    }

    @Test
    public void getSelectedIntersection1(){
        

        gb.enableIntersectionButtons(true);

        gb.intersectionButtons.get(0).doClick();

        assertEquals(0,gb.getSelectedIntersection());

    }

    @Test
    public void getSelectedIntersection2(){
        

        gb.enableIntersectionButtons(true);


        assertEquals(-1,gb.getSelectedIntersection());

    }

    @Test
    public void getSelectedIntersection3(){
        

        gb.enableIntersectionButtons(true);

        gb.intersectionButtons.get(0).doClick();

        assertEquals(0,gb.getSelectedIntersection());

        assertEquals(-1,gb.getSelectedIntersection());

    }
}
